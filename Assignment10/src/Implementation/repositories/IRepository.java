package Implementation.repositories;

import java.sql.Connection;

import Implementation.dtos.DTOBase;

public interface IRepository<TDTO extends DTOBase> {

	Connection getConnection();

	void add(TDTO dto);

	void update(TDTO dto);
	
	void addOrUpdate(TDTO dto);

	void delete(TDTO dto);

	TDTO findById(int id);

	void beginTransaction();

	void commitTransaction();

	void rollbackTransaction();
	
	int getCount();
	
	boolean exists(TDTO dto);
}