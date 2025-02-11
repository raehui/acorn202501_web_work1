<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- toast editor 사용 css, javascript -->
<link rel="stylesheet" href="https://uicdn.toast.com/calendar/latest/toastui-calendar.min.css" />
<script src="https://uicdn.toast.com/calendar/latest/toastui-calendar.min.js"></script>
</head>
<body>
	<h1>Toast UI Calendar 사용하기</h1>
	<div id="calendar" style="height: 800px"></div>
	
	<script>
		const Calendar = tui.Calendar;
		const container = document.getElementById('calendar');
		const options = {
		  defaultView: 'month',
		  useFormPopup: true
		};
		
		const calendar = new Calendar(container, options);
		
		calendar.createEvents([
			  {
			    id: 'event1',
			    calendarId: 'cal2',
			    title: '주간 회의',
			    start: '2022-06-07T09:00:00',
			    end: '2022-06-07T10:00:00',
			  },
			  {
			    id: 'event2',
			    calendarId: 'cal1',
			    title: '점심 약속',
			    start: '2022-06-08T12:00:00',
			    end: '2022-06-08T13:00:00',
			  },
			  {
			    id: 'event3',
			    calendarId: 'cal2',
			    title: '휴가',
			    start: '2022-06-08',
			    end: '2022-06-10',
			    isAllday: true,
			    category: 'allday',
			  },
			]);
	
		
	</script>
</body>
</html>
