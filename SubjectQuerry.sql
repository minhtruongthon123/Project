select s.Name from [dbo].[Group] g inner join [dbo].[Enrollment] e on e.GroupID=g.ID
 inner join [dbo].[Subject] s on s.ID=g.SubjectID where e.StudentID ='HE160497'