select c.name as Компания, count(p.company_id) as кол_во_сотрудников 
	from company c 
	join person p on p.company_id = c.id 
	group by c.name 
	order by count(*) 
	desc fetch first 1 row with ties;