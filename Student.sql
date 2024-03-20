select * from Student where Email='minhndhe160497@gmail.com'
select s.ID,s.Name,s.Dob,s.Email,s.Gender,s.Phone from Student s 
inner join [dbo].Enrollment e on e.StudentID=s.ID where e.GroupID=11