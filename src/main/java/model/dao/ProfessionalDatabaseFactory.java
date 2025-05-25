package model.dao;

import enums.DatabaseType;

public class ProfessionalDatabaseFactory {

private DatabaseType type;
	
	public ProfessionalDatabaseFactory() {
		if(type == null) {
			type = DatabaseType.MYSQL;
			
		}
	}
	
	public ProfessionalDatabaseFactory(DatabaseType type) {
		this.type = type;
	}
	public static DatabaseProfessionalDAO factory(DatabaseType type) {
		switch (type) {
		case MYSQL: {
			return new DatabaseProfessionalImp();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}
}
