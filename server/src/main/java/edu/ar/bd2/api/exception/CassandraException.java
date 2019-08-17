package edu.ar.bd2.api.exception;

@SuppressWarnings("serial")
public class CassandraException extends ServiceException {

	public CassandraException(String message) {
		super(message);
	}
}
