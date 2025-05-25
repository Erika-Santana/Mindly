package model.dao;

import enums.DatabaseType;

public class UserDatabaseFactory {
	
	private DatabaseType type;
	
	public UserDatabaseFactory() {
		if(type == null) {
			type = DatabaseType.MYSQL;
			
		}
	}
	
	public UserDatabaseFactory(DatabaseType type) {
		this.type = type;
	}
	
	public static DatabaseUserDAO factory(DatabaseType type) {
		switch (type) {
		case MYSQL: {
			return new DatabaseUserImp();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}

}
