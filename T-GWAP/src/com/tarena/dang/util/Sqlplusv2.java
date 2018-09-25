package com.tarena.dang.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Sqlplusv2 {
	private static String dFormat = "yyyy-MM-dd";

	private static Connection con;

	private static Map<String, Integer> widths = new HashMap<String, Integer>();;

	public static void setDFormat(String format) {
		dFormat = format;
	}

	public static void main(String[] args) throws Exception {
		con = MysqlConnectionUtils.getConnection();
		con.setAutoCommit(false);
		excuteSql();
		con.close();
	}

	public static void excuteSql() throws Exception {
		try {
			Scanner scan = new Scanner(System.in);
			Statement stmt = con.createStatement();
			String sql, input;
			while (true) {
				sql = "";
				System.out.print("Mysql>");
				input = scan.nextLine();
				ResultSet rs = null;
				if (input.toLowerCase().startsWith("set")
						|| input.toLowerCase().startsWith("desc")) {
					sets(input);
					continue;
				}
				if (input.trim().equals("exit")) {
					System.out.println("谢谢使用");
					break;
				} else {
					int row = 2;
					while (true) {
						sql = sql + " " + input;
						if (input.endsWith(";")) {
							break;
						}
						System.out.print("第" + (row++) + "行:");
						input = scan.nextLine();

					}
					sql = sql.substring(1, sql.length() - 1);
					if (stmt.execute(sql)) {
						rs = stmt.getResultSet();
						init(rs);
						resultShow(rs);
					} else {
						int rows = stmt.getUpdateCount();
						sql = sql.toLowerCase();
						if (sql.startsWith("insert")) {
							System.out.println(rows + " rows created.");
						} else if (sql.startsWith("update")) {
							System.out.println(rows + " rows updated.");
						} else if (sql.startsWith("delete")) {
							System.out.println(rows + " rows deleted.");
						} else if (sql.trim().equals("commit")) {
							con.commit();
							System.out.println("commit completed.");
						} else if (sql.trim().equals("rollback")) {
							con.rollback();
							System.out.println("rollback completed.");
						} else if (sql.startsWith("drop")) {
							System.out.println("dropped.");
						} else if (sql.startsWith("create")) {
							System.out.println("created.");
						} else if (sql.startsWith("alter")) {
							System.out.println("altered.");
						}
					}
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			excuteSql();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public static void descTable(String tableName) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "select * from " + tableName + " where 1=0";
		ResultSet rs = stmt.executeQuery(sql);
		System.out.printf("%-20s", "Name");
		System.out.printf("%-20s", "Null?");
		System.out.printf("%-20s", "Type");
		System.out.println();
		for (int col = 1; col <= rs.getMetaData().getColumnCount(); col++) {
			System.out.printf("%-20s", rs.getMetaData().getColumnName(col));
			if (rs.getMetaData().isNullable(col) == 0) {
				System.out.printf("%-20s", "Not Null");
			} else {
				System.out.printf("%-20s", " ");
			}
			System.out.printf("%-20s", rs.getMetaData().getColumnTypeName(col)/* +rs.getMetaData().getPrecision(col) */);
			System.out.println();
		}
		System.out.println();

	}

	public static void setWidths(String colName, int width) {
		widths.put(colName, width);
	}

	public static void init(ResultSet rs) throws Exception {
		for (int col = 1; col <= rs.getMetaData().getColumnCount(); col++) {
			int width = rs.getMetaData().getColumnDisplaySize(col);
			if (rs.getMetaData().getColumnTypeName(col)
					.equalsIgnoreCase("date")) {
				width = width + 10;
			}
			if (widths.get(rs.getMetaData().getColumnName(col)) == null) {
				setWidths(rs.getMetaData().getColumnName(col), width);
			}
		}
	}

	public static void sets(String input) throws SQLException {
		String cmds[] = input.split(" ");
		int length=cmds.length;
		if (length==4 && cmds[0].equalsIgnoreCase("set") && cmds[2].equalsIgnoreCase("for")) {
			setWidths(cmds[1].toUpperCase(), Integer.parseInt(cmds[3]));
			System.out.println(cmds[1].toUpperCase() + "宽度更改为"
					+ Integer.parseInt(cmds[3]));
		} else if (length==3&&cmds[1].equalsIgnoreCase("dateformat")) {
			setDFormat(cmds[2].toLowerCase());
			System.out
					.println("DateFormat changed to " + cmds[2].toLowerCase());
		} else if (length==2&&cmds[0].equalsIgnoreCase("desc")) {
			descTable(cmds[1]);
		} else if (length==3&&cmds[1].equalsIgnoreCase("autoCommit")) {
			if (cmds[2].equalsIgnoreCase("on")) {
				con.setAutoCommit(true);
				System.out.println("AutoCommit.");
			} else if (cmds[2].equalsIgnoreCase("off")) {
				con.setAutoCommit(false);
				System.out.println("No AutoCommit.");
			}
		} else {
			System.out.println("对不起,您输入的命令不存在!");
		}

	}

	public static void resultShow(ResultSet rs) throws Exception {
		String colstr;
		SimpleDateFormat sdf = new SimpleDateFormat(dFormat);
		if (!rs.next()) {
			System.out.println("no rows selected.");
			return;
		}
		for (int col = 1; col <= rs.getMetaData().getColumnCount(); col++) {

			System.out.printf("%-"
					+ widths.get(rs.getMetaData().getColumnName(col)) + "s", rs
					.getMetaData().getColumnName(col));
		}
		System.out.println();
		do {
			for (int col = 1; col <= rs.getMetaData().getColumnCount(); col++) {
				colstr = rs.getString(col);
				if (rs.getMetaData().getColumnTypeName(col).equalsIgnoreCase(
						"date")
						&& rs.getDate(col) != null) {
					colstr = sdf.format(rs.getDate(col));
				}
				System.out.printf(
						"%-" + widths.get(rs.getMetaData().getColumnName(col))
								+ "s", colstr);
			}
			System.out.println();
		} while (rs.next());

	}

}
