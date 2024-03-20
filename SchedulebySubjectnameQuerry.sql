SELECT DATENAME(dw, sch.Date) AS DayOfWeek,FORMAT(CONVERT(DATE, sch.Date, 23), 'dd-MM-yyyy') AS Date,
       sch.TimeStart, sch.TimeEnd, sch.RoomID, g.Name AS ClassName, 
       sb.Name AS SubjectName
FROM [dbo].[Enrollment] e
INNER JOIN [dbo].[Schedule] sch ON e.GroupID = sch.GroupID
INNER JOIN [dbo].[Group] g ON e.GroupID = g.ID
INNER JOIN [dbo].[Subject] sb ON g.SubjectID = sb.ID
WHERE sb.Name in ('PRF192','MAE101') and e.StudentID='HE160497'