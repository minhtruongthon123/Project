Select sch.ID,FORMAT(CONVERT(DATE, sch.Date, 23), 'dd-MM-yyyy') AS Date,sch.TimeStart,sch.TimeEnd,sch.RoomID,t.ID as Intructor,a.IsPresent,a.comment 
 from Attendance a inner join Schedule sch on sch.ID=a.ScheduleID 
inner join[dbo].[Group] g on g.ID = sch.GroupID 
inner join [dbo].[Subject] s on g.SubjectID = s.ID 
inner join [dbo].[Teacher] t on t.ID=g.TeacherID
WHERE a.StudentID='HE160497' and s.Name='PRF192'
Select sch.ID,FORMAT(CONVERT(DATE, sch.Date, 23), 'dd-MM-yyyy') AS Date,sch.TimeStart,sch.TimeEnd,sch.RoomID,t.ID as Intructor
 from Attendance a inner join Schedule sch on sch.ID=a.ScheduleID 
inner join[dbo].[Group] g on g.ID = sch.GroupID 
inner join [dbo].[Subject] s on g.SubjectID = s.ID 
inner join [dbo].[Teacher] t on t.ID=g.TeacherID
WHERE t.ID='thangnt44' and g.ID=11 group by sch.ID,FORMAT(CONVERT(DATE, sch.Date, 23), 'dd-MM-yyyy'),sch.TimeStart,sch.TimeEnd,sch.RoomID,t.ID
Select sch.ID, e.StudentID,s.Name,a.IsPresent,a.comment from Attendance a 
inner join Schedule sch on sch.ID=a.ScheduleID 
inner join[dbo].[Group] g on g.ID = sch.GroupID
inner join [dbo].[Teacher] t on t.ID=g.TeacherID
inner join[dbo].[Enrollment] e on g.ID=e.GroupID
inner join [dbo].[Student] s on e.StudentID=s.ID
WHERE g.TeacherID='thangnt44' and g.ID=11and sch.ID=25 group by sch.ID, e.StudentID,s.Name,a.IsPresent,a.comment
select sch.ID,a.IsPresent from Attendance a
inner join Schedule sch on sch.ID=a.ScheduleID 
inner join[dbo].[Group] g on g.ID = sch.GroupID
inner join [dbo].[Teacher] t on t.ID=g.TeacherID
inner join[dbo].[Enrollment] e on g.ID=e.GroupID
inner join [dbo].[Student] s on e.StudentID=s.ID
where a.StudentID='HE160043' and g.ID=11 group by sch.ID,a.IsPresent order by sch.ID ASC 
Update Attendance 
set IsPresent=1,Comment='Good'
where ScheduleID=11 and StudentID='HE166486'