package test.post.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.post.dto.CommentDto;
import test.util.DbcpBean;

public class CommentDao {
   private static CommentDao dao;

   static {
        dao = new CommentDao();
   }

   private CommentDao() {}
   
   public static CommentDao getInstance() {
	   return dao;
   }
   
   /** 댓글 개수 가져오기 */
   public int getCount(long postNum) {
       int count = 0;
       String sql = "SELECT NVL(MAX(ROWNUM), 0) FROM comments WHERE postNum=?";
       
       try (Connection conn = new DbcpBean().getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
           pstmt.setLong(1, postNum);
           try (ResultSet rs = pstmt.executeQuery()) {
               if (rs.next()) {
                   count = rs.getInt(1);
               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return count;
   }

   /** 댓글 수정하기 */
   public boolean update(CommentDto dto) {
       String sql = "UPDATE comments SET content=? WHERE num=?";
       try (Connection conn = new DbcpBean().getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
           pstmt.setString(1, dto.getContent());
           pstmt.setLong(2, dto.getNum());
           return pstmt.executeUpdate() > 0;
       } catch (Exception e) {
           e.printStackTrace();
       }
       return false;
   }

   /** 댓글 삭제 (deleted 필드를 'yes'로 업데이트) */
   //yes이면 삭제된 댓글입니다. 출력 -> 구조를 유지하기 위해서
   public boolean delete(long num) {
       String sql = "UPDATE comments SET deleted='yes' WHERE num=?";
       try (Connection conn = new DbcpBean().getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
           pstmt.setLong(1, num);
           return pstmt.executeUpdate() > 0;
       } catch (Exception e) {
           e.printStackTrace();
       }
       return false;
   }

   /** 특정 댓글 정보 가져오기 */
   //조인해서 프로필 이미지 가져오기
   public CommentDto getData(long num) {
	   String sql = """
			    SELECT comments.num, writer, content, targetWriter, postNum, 
			           parentNum, deleted, comments.createdAt, profileImage 
			    FROM comments 
			    JOIN users ON comments.writer = users.userName
			    WHERE comments.num=?
			    """;

       CommentDto dto = null;
       
       try (Connection conn = new DbcpBean().getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
           pstmt.setLong(1, num);
           try (ResultSet rs = pstmt.executeQuery()) {
               if (rs.next()) {
                   dto = new CommentDto();
                   dto.setNum(rs.getLong("num"));
                   dto.setWriter(rs.getString("writer"));
                   dto.setContent(rs.getString("content"));
                   dto.setTargetWriter(rs.getString("targetWriter"));
                   dto.setPostNum(rs.getLong("postNum"));
                   dto.setParentNum(rs.getLong("parentNum"));
                   dto.setDeleted(rs.getString("deleted"));
                   dto.setCreatedAt(rs.getString("createdAt"));
                   dto.setProfileImage(rs.getString("profileImage"));
               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return dto;
   }

   /** 댓글 목록 가져오기 (페이징 지원) */
   public List<CommentDto> getList(CommentDto dto) {
       List<CommentDto> list = new ArrayList<>();
       String sql = """
    		    SELECT * FROM (
    		        SELECT result1.*, ROWNUM AS rnum FROM (
    		            SELECT comments.num, writer, content, targetWriter, postNum, 
    		                   parentNum, deleted, comments.createdAt, profileImage
    		            FROM comments
    		            JOIN users ON comments.writer = users.userName
    		            WHERE postNum=?
    		            ORDER BY parentNum DESC, num ASC
    		        ) result1
    		    ) 
    		    WHERE rnum BETWEEN ? AND ?
    		    """;
       
       try (Connection conn = new DbcpBean().getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
           pstmt.setLong(1, dto.getPostNum());
           pstmt.setInt(2, dto.getStartRowNum());
           pstmt.setInt(3, dto.getEndRowNum());
           try (ResultSet rs = pstmt.executeQuery()) {
               while (rs.next()) {
                   CommentDto comment = new CommentDto();
                   comment.setNum(rs.getLong("num"));
                   comment.setWriter(rs.getString("writer"));
                   comment.setContent(rs.getString("content"));
                   comment.setTargetWriter(rs.getString("targetWriter"));
                   comment.setPostNum(rs.getLong("postNum"));
                   comment.setParentNum(rs.getLong("parentNum"));
                   comment.setDeleted(rs.getString("deleted"));
                   comment.setCreatedAt(rs.getString("createdAt"));
                   comment.setProfileImage(rs.getString("profileImage"));
                   list.add(comment);
               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return list;
   }

   /** 댓글 추가하기 */
   // 프라이머리키를 미리 만들어서 dto 담아서 글번호, 부모 글번호 사용하겠다.
   public boolean insert(CommentDto dto) {
	   String sql = """
			    INSERT INTO comments 
			        (num, writer, content, targetWriter, postNum, parentNum, createdAt) 
			    VALUES (?, ?, ?, ?, ?, ?, SYSDATE)
			    """;

       try (Connection conn = new DbcpBean().getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
           pstmt.setLong(1, dto.getNum());
           pstmt.setString(2, dto.getWriter());
           pstmt.setString(3, dto.getContent());
           pstmt.setString(4, dto.getTargetWriter());
           pstmt.setLong(5, dto.getPostNum());
           pstmt.setLong(6, dto.getParentNum());
           return pstmt.executeUpdate() > 0;
       } catch (Exception e) {
           e.printStackTrace();
       }
       return false;
   }

   /** 새로운 댓글 번호 가져오기 */
   //저장하기 전에 미리 글번호를 가져와서 사용
   public long getSequence() {
       long newNum = 0;
       String sql = "SELECT comments_seq.NEXTVAL FROM DUAL";
       try (Connection conn = new DbcpBean().getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
           if (rs.next()) {
               newNum = rs.getLong(1);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return newNum;
   }
}