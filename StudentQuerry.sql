select st.ID, st.Name,st.Email,st.Phone from Student st inner join Account ac on (st.Email=ac.Username) where ac.Username='minhndhe160497@gmail.com'
select s.ID,s.Name,sb.Name,g.TeacherID,g.Name from Student s 
inner join [dbo].Enrollment e on s.ID=e.StudentID
inner join [dbo].[Group] g on e.GroupID=g.ID 
inner join [dbo].[Subject] sb on g.SubjectID=sb.ID