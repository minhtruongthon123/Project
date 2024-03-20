select e.GroupID,e.StudentID from [dbo].[Enrollment] e inner join [dbo].[Student]  s on s.ID=e.StudentID where s.ID='HE160497'
