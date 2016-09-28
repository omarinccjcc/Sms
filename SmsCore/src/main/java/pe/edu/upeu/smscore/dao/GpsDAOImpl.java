package pe.edu.upeu.smscore.dao;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.object.StoredProcedure;

public class GpsDAOImpl implements GpsDAO {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void selectRegs() {

		String getDBUSERCursorSql = "{call GetEmpRS (?)}";

		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM PERSONA";
		// System.out.println(jdbcTemplate.queryForList(sql));

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("GetEmpRS");

		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("nombreIn", "Omar");
		// inParamMap.put("lastName", "Chaudhari");
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);

		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);

		System.out.println(simpleJdbcCallResult);
		System.out.println(simpleJdbcCallResult.get("P_RECORDSET"));
		// p_recordset
		// Employee employee = (Employee) jdbcTemplate.queryForObject(
		// sql, new Object[] { id }, new EmployeeRowMapper());
		 callStoredProcedure();

//		CallProcedure procedure = new CallProcedure(dataSource, "getPersona");
//		String cantidad = procedure.getPersona();
	}

	public void callStoredProcedure() {

		SimpleJdbcCall nombreIn = new SimpleJdbcCall(dataSource).withProcedureName("getPersona");

		SqlParameterSource in = new MapSqlParameterSource().addValue("id_", 1);

		Map<String, Object> outt = nombreIn.execute(in);

		System.out.println("outt " + outt );
		System.out.println("outt " + outt.get("APEMATERNOIN"));

	}

}
