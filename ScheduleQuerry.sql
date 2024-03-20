SELECT DATENAME(dw, sch.Date) AS DayOfWeek,FORMAT(CONVERT(DATE, sch.Date, 23), 'dd-MM-yyyy') AS Date,
       sch.TimeStart, sch.TimeEnd, sch.RoomID, g.Name AS ClassName, 
       sb.Name AS SubjectName
FROM [dbo].[Enrollment] e
INNER JOIN [dbo].[Schedule] sch ON e.GroupID = sch.GroupID
INNER JOIN [dbo].[Group] g ON e.GroupID = g.ID
INNER JOIN [dbo].[Subject] sb ON g.SubjectID = sb.ID
WHERE e.StudentID='HE160497'
SELECT DATENAME(dw, sch.Date) AS DayOfWeek,FORMAT(CONVERT(DATE, sch.Date, 23), 'dd-MM-yyyy') AS Date,
sch.TimeStart, sch.TimeEnd, sch.RoomID, g.Name AS ClassName,
sb.Name AS SubjectName
FROM [dbo].[Schedule] sch
INNER JOIN [dbo].[Enrollment] e ON e.GroupID = sch.GroupID
INNER JOIN [dbo].[Group] g ON e.GroupID = g.ID
INNER JOIN [dbo].[Subject] sb ON g.SubjectID = sb.ID
WHERE g.TeacherID='thangnt44' group by sch.Date,sch.TimeStart, sch.TimeEnd, sch.RoomID, g.Name, sb.Name 