<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<h1>Toast UI Calender 사용하기</h1>
	<div id="calendar" style="height: 800px"></div>
	
	<script>
		const Calendar = tui.Calendar;
		const calendar = new Calendar('#calendar', {
			  defaultView: 'week',
			  template: {
			    time(event) {
			      const { start, end, title } = event;
	
			      return `<span style="color: white;">${formatTime(start)}~${formatTime(end)} ${title}</span>`;
			    },
			    allday(event) {
			      return `<span style="color: gray;">${event.title}</span>`;
			    },
			  },
			  calendars: [
			    {
			      id: 'cal1',
			      name: 'Personal',
			      backgroundColor: '#03bd9e',
			    },
			    {
			      id: 'cal2',
			      name: 'Work',
			      backgroundColor: '#00a9ff',
			    },
			  ],
			});
	</script>
</body>
</html>