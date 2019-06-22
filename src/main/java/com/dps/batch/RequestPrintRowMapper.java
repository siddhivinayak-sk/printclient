package com.dps.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dps.entities.PrintRequest;

public class RequestPrintRowMapper implements RowMapper<PrintRequest> {

	@Override
	public PrintRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		PrintRequest pr = new PrintRequest();
		return pr;
	}

}
