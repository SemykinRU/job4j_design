select p.name as Персона, c.name as Компания 
	from person p 
	join company c on p.company_id = c.id 
	where c.id != 5;
