package pe.edu.upeu.smscore.dao;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class CallProcedure extends StoredProcedure {
	public CallProcedure(DataSource dataSource, String storedName) {
		super(dataSource, storedName);
	}

	public String getPersona() {
		// super(dataSource, STORED_RPTA_IDENTIFICACION);
		declareParameter(new SqlParameter("1", Types.VARCHAR));
//		declareParameter(new SqlParameter("2", Types.VARCHAR));
//		declareParameter(new SqlParameter("3", Types.VARCHAR));
//		declareParameter(new SqlParameter("4", Types.VARCHAR));
//		declareParameter(new SqlParameter("5", Types.INTEGER));
//
//		declareParameter(new SqlOutParameter("6", Types.VARCHAR));
//		declareParameter(new SqlOutParameter("7", Types.VARCHAR));
//		declareParameter(new SqlOutParameter("8", Types.VARCHAR));
//		declareParameter(new SqlOutParameter("9", Types.VARCHAR));
//		declareParameter(new SqlOutParameter("10", Types.VARCHAR));
		compile();

		Map inParameters = new HashMap(1);
		inParameters.put("1",1);


		Map outParameters = execute(inParameters);
		if (outParameters.size() > 0) {
			return "" + outParameters.get("2") + "-" + outParameters.get("3") + "";
		} else {
			return "ERROR";
		}
	}
}
