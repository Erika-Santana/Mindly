package model.dao;

import enums.DatabaseType;

public class AppointmentDatabaseFactory {

	private DatabaseType type;

	public AppointmentDatabaseFactory() {
		if (type == null) {
			type = DatabaseType.MYSQL;

		}
	}

	public AppointmentDatabaseFactory(DatabaseType type) {
		this.type = type;
	}

	public static DatabaseAppointmentsDAO factory(DatabaseType type) {
		switch (type) {
		case MYSQL: {
			return new DatabaseAppointmentImp();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}
}
