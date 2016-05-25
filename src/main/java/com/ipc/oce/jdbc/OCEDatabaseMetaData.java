/**
 * 
 */
package com.ipc.oce.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.RowIdLifetime;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jinterop.dcom.common.JIException;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.PropertiesReader;
import com.ipc.oce.metadata.OCConfigurationMetadataObject;
import com.ipc.oce.metadata.OCNumberQualifiers;
import com.ipc.oce.metadata.OCType;
import com.ipc.oce.metadata.OCTypeDescription;
import com.ipc.oce.metadata.collection.OCMetadataAttributeCollection;
import com.ipc.oce.metadata.collection.OCMetadataCatalogCollection;
import com.ipc.oce.metadata.collection.OCMetadataDocumentCollection;
import com.ipc.oce.metadata.objects.OCAttributeMetadataObject;
import com.ipc.oce.metadata.objects.OCCatalogMetadataObject;
import com.ipc.oce.metadata.objects.OCDocumentMetadataObject;

/**
 * @author Konovalov
 * 
 */
public class OCEDatabaseMetaData implements DatabaseMetaData {

	private static final transient Log LOG = LogFactory
			.getLog(OCEDatabaseMetaData.class);

	private static final XPathFactory FACTORY = XPathFactory.newInstance();

	/**
	 * 
	 */
	private static final String SCHEMA_CATALOG = "Catalog";
	/**
	 * 
	 */
	private static final String SCHEMA_DOCUMENT = "Document";

	private OCEConnection connection = null;

	protected OCEDatabaseMetaData(OCEConnection connection) {
		this.connection = connection;
	}

	@SuppressWarnings("unchecked")
	public <T> T unwrap(Class<T> paramClass) throws SQLException {
		if (paramClass.equals(OCApp.class)) {
			return (T) connection.getApplication();
		}
		return null;
	}

	public boolean isWrapperFor(Class<?> paramClass) throws SQLException {
		return (paramClass.equals(OCApp.class));
	}

	public boolean allProceduresAreCallable() throws SQLException {
		return false;
	}

	public boolean allTablesAreSelectable() throws SQLException {
		return false;
	}

	public String getURL() throws SQLException {
		return connection.getConnectionURL();
	}

	public String getUserName() throws SQLException {
		return connection.getConnectionProperties().getProperty(
				PropertiesReader.OCE_CFG_1CDB_USER);
	}

	public boolean isReadOnly() throws SQLException {
		return false;
	}

	public boolean nullsAreSortedHigh() throws SQLException {
		return false;
	}

	public boolean nullsAreSortedLow() throws SQLException {
		return false;
	}

	public boolean nullsAreSortedAtStart() throws SQLException {
		return false;
	}

	public boolean nullsAreSortedAtEnd() throws SQLException {
		return false;
	}

	public String getDatabaseProductName() throws SQLException {
		try {
			return connection.getApplication().getMetadata().getName();
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	public String getDatabaseProductVersion() throws SQLException {
		try {
			return connection.getApplication().getMetadata().getVersion();
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	public String getDriverName() throws SQLException {
		return OCEDriver.DRIVER_NAME;
	}

	public String getDriverVersion() throws SQLException {
		Driver driver = connection.getDriver();
		return driver.getMajorVersion() + "." + driver.getMinorVersion();
	}

	public int getDriverMajorVersion() {
		Driver driver = connection.getDriver();
		return driver.getMajorVersion();
	}

	public int getDriverMinorVersion() {
		Driver driver = connection.getDriver();
		return driver.getMinorVersion();
	}

	public boolean usesLocalFiles() throws SQLException {
		return false;
	}

	public boolean usesLocalFilePerTable() throws SQLException {
		return false;
	}

	public boolean supportsMixedCaseIdentifiers() throws SQLException {
		return true;
	}

	public boolean storesUpperCaseIdentifiers() throws SQLException {
		return true;
	}

	public boolean storesLowerCaseIdentifiers() throws SQLException {
		return true;
	}

	public boolean storesMixedCaseIdentifiers() throws SQLException {
		return true;
	}

	public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException {
		return false;
	}

	public boolean storesUpperCaseQuotedIdentifiers() throws SQLException {
		return false;
	}

	public boolean storesLowerCaseQuotedIdentifiers() throws SQLException {
		return false;
	}

	public boolean storesMixedCaseQuotedIdentifiers() throws SQLException {
		return false;
	}

	public String getIdentifierQuoteString() throws SQLException {
		return " "; // [bug-fix 2011.01.18] space by jdbc spec
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#getSQLKeywords()
	 */

	public String getSQLKeywords() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#getNumericFunctions()
	 */

	public String getNumericFunctions() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#getStringFunctions()
	 */

	public String getStringFunctions() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#getSystemFunctions()
	 */

	public String getSystemFunctions() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#getTimeDateFunctions()
	 */

	public String getTimeDateFunctions() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#getSearchStringEscape()
	 */

	public String getSearchStringEscape() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#getExtraNameCharacters()
	 */

	public String getExtraNameCharacters() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean supportsAlterTableWithAddColumn() throws SQLException {
		return false;
	}

	public boolean supportsAlterTableWithDropColumn() throws SQLException {
		return false;
	}

	public boolean supportsColumnAliasing() throws SQLException {
		return true;
	}

	public boolean nullPlusNonNullIsNull() throws SQLException {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#supportsConvert()
	 */

	public boolean supportsConvert() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#supportsConvert(int, int)
	 */

	public boolean supportsConvert(int paramInt1, int paramInt2)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean supportsTableCorrelationNames() throws SQLException {
		return true;
	}

	public boolean supportsDifferentTableCorrelationNames() throws SQLException {
		return false;
	}

	public boolean supportsExpressionsInOrderBy() throws SQLException {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#supportsOrderByUnrelated()
	 */

	public boolean supportsOrderByUnrelated() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean supportsGroupBy() throws SQLException {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#supportsGroupByUnrelated()
	 */

	public boolean supportsGroupByUnrelated() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean supportsGroupByBeyondSelect() throws SQLException {
		return true;
	}

	public boolean supportsLikeEscapeClause() throws SQLException {
		return true;
	}

	public boolean supportsMultipleResultSets() throws SQLException {
		return true;
	}

	public boolean supportsMultipleTransactions() throws SQLException {
		return false;
	}

	public boolean supportsNonNullableColumns() throws SQLException {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#supportsMinimumSQLGrammar()
	 */

	public boolean supportsMinimumSQLGrammar() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#supportsCoreSQLGrammar()
	 */

	public boolean supportsCoreSQLGrammar() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#supportsExtendedSQLGrammar()
	 */

	public boolean supportsExtendedSQLGrammar() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#supportsANSI92EntryLevelSQL()
	 */

	public boolean supportsANSI92EntryLevelSQL() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#supportsANSI92IntermediateSQL()
	 */

	public boolean supportsANSI92IntermediateSQL() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#supportsANSI92FullSQL()
	 */

	public boolean supportsANSI92FullSQL() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#supportsIntegrityEnhancementFacility()
	 */

	public boolean supportsIntegrityEnhancementFacility() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#supportsOuterJoins()
	 */

	public boolean supportsOuterJoins() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#supportsFullOuterJoins()
	 */

	public boolean supportsFullOuterJoins() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#supportsLimitedOuterJoins()
	 */

	public boolean supportsLimitedOuterJoins() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public String getSchemaTerm() throws SQLException {
		return "schema"; // like in db2
	}

	public String getProcedureTerm() throws SQLException {
		return "Procedure"; // TODO FIX IT
	}

	public String getCatalogTerm() throws SQLException {
		return null;
	}

	public boolean isCatalogAtStart() throws SQLException {
		return true;
	}

	public String getCatalogSeparator() throws SQLException {
		return ".";
	}

	public boolean supportsSchemasInDataManipulation() throws SQLException {
		return true;
	}

	public boolean supportsSchemasInProcedureCalls() throws SQLException {
		return false;
	}

	public boolean supportsSchemasInTableDefinitions() throws SQLException {
		return true;
	}

	public boolean supportsSchemasInIndexDefinitions() throws SQLException {
		return false;
	}

	public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {
		return false;
	}

	public boolean supportsCatalogsInDataManipulation() throws SQLException {
		return false;
	}

	public boolean supportsCatalogsInProcedureCalls() throws SQLException {
		return false;
	}

	public boolean supportsCatalogsInTableDefinitions() throws SQLException {
		return false;
	}

	public boolean supportsCatalogsInIndexDefinitions() throws SQLException {
		return false;
	}

	public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
		return false;
	}

	public boolean supportsPositionedDelete() throws SQLException {
		return false;
	}

	public boolean supportsPositionedUpdate() throws SQLException {
		return false;
	}

	public boolean supportsSelectForUpdate() throws SQLException {
		return false;
	}

	public boolean supportsStoredProcedures() throws SQLException {
		return false;
	}

	public boolean supportsSubqueriesInComparisons() throws SQLException {
		return true;
	}

	public boolean supportsSubqueriesInExists() throws SQLException {
		return true;
	}

	public boolean supportsSubqueriesInIns() throws SQLException {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#supportsSubqueriesInQuantifieds()
	 */

	public boolean supportsSubqueriesInQuantifieds() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean supportsCorrelatedSubqueries() throws SQLException {
		return true;
	}

	public boolean supportsUnion() throws SQLException {
		return true;
	}

	public boolean supportsUnionAll() throws SQLException {
		return true;
	}

	public boolean supportsOpenCursorsAcrossCommit() throws SQLException {
		return true;
	}

	public boolean supportsOpenCursorsAcrossRollback() throws SQLException {
		return true;
	}

	public boolean supportsOpenStatementsAcrossCommit() throws SQLException {
		return true;
	}

	public boolean supportsOpenStatementsAcrossRollback() throws SQLException {
		return true;
	}

	public int getMaxBinaryLiteralLength() throws SQLException {
		return 0;
	}

	public int getMaxCharLiteralLength() throws SQLException {
		return 0;
	}

	public int getMaxColumnNameLength() throws SQLException {
		return 0;
	}

	public int getMaxColumnsInGroupBy() throws SQLException {
		return 0;
	}

	public int getMaxColumnsInIndex() throws SQLException {
		return 0;
	}

	public int getMaxColumnsInOrderBy() throws SQLException {
		return 0;
	}

	public int getMaxColumnsInSelect() throws SQLException {
		return 0;
	}

	public int getMaxColumnsInTable() throws SQLException {
		return 0;
	}

	public int getMaxConnections() throws SQLException {
		return 0;
	}

	public int getMaxCursorNameLength() throws SQLException {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#getMaxIndexLength()
	 */

	public int getMaxIndexLength() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getMaxSchemaNameLength() throws SQLException {
		return 0;
	}

	public int getMaxProcedureNameLength() throws SQLException {
		return 0;
	}

	public int getMaxCatalogNameLength() throws SQLException {
		return 0;
	}

	public int getMaxRowSize() throws SQLException {
		return 0;
	}

	public boolean doesMaxRowSizeIncludeBlobs() throws SQLException {
		return true; // unlimited MaxRowSize
	}

	public int getMaxStatementLength() throws SQLException {
		return 0;
	}

	public int getMaxStatements() throws SQLException {
		return 0;
	}

	public int getMaxTableNameLength() throws SQLException {
		return 0;
	}

	public int getMaxTablesInSelect() throws SQLException {
		return 0;
	}

	public int getMaxUserNameLength() throws SQLException {
		return 0;
	}

	public int getDefaultTransactionIsolation() throws SQLException {
		return Connection.TRANSACTION_READ_COMMITTED;
	}

	public boolean supportsTransactions() throws SQLException {
		return true;
	}

	public boolean supportsTransactionIsolationLevel(int paramInt)
			throws SQLException {
		return (paramInt == Connection.TRANSACTION_READ_COMMITTED);
	}

	public boolean supportsDataDefinitionAndDataManipulationTransactions()
			throws SQLException {
		return true;
	}

	public boolean supportsDataManipulationTransactionsOnly()
			throws SQLException {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#dataDefinitionCausesTransactionCommit()
	 */

	public boolean dataDefinitionCausesTransactionCommit() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean dataDefinitionIgnoredInTransactions() throws SQLException {
		return false;
	}

	public ResultSet getProcedures(String paramString1, String paramString2,
			String paramString3) throws SQLException {
		LocalResultSet lrs = LocalResultSet.createEmptyResultSet(new String[] {
				"PROCEDURE_CAT", "PROCEDURE_SCHEM", "PROCEDURE_NAME",
				"RESERVED1", "RESERVED2", "RESERVED3", "REMARKS",
				"PROCEDURE_TYPE", "SPECIFIC_NAME" }, connection);
		return lrs;
	}

	public ResultSet getProcedureColumns(String paramString1,
			String paramString2, String paramString3, String paramString4)
			throws SQLException {
		LocalResultSet lrs = LocalResultSet.createEmptyResultSet(new String[] {
				"PROCEDURE_CAT", "PROCEDURE_SCHEM", "PROCEDURE_NAME",
				"COLUMN_NAME", "COLUMN_TYPE", "DATA_TYPE", "TYPE_NAME",
				"PRECISION", "LENGTH", "SCALE", "RADIX", "NULLABLE", "REMARKS",
				"COLUMN_DEF", "SQL_DATA_TYPE", "SQL_DATETIME_SUB",
				"CHAR_OCTET_LENGTH", "ORDINAL_POSITION", "IS_NULLABLE",
				"SPECIFIC_NAME"

		}, connection);

		return lrs;
	}

	/**
	 * Пока реализованы только Document и(или) Catalog
	 */
	public ResultSet getTables(String catalog, String schemaPattern,
			String tableNamePattern, String[] types) throws SQLException {

		OCApp app = connection.getApplication();
		LocalResultSet lrs = LocalResultSet.createEmptyResultSet(new String[] {
				"TABLE_CAT", "TABLE_SCHEM", "TABLE_NAME", "TABLE_TYPE",
				"REMARKS", "TYPE_CAT", "TYPE_SCHEM", "TYPE_NAME",
				"SELF_REFERENCING_COL_NAME", "REF_GENERATION" }, connection);
		try {
			OCConfigurationMetadataObject metadata = app.getMetadata();

			if (schemaPattern == null || schemaPattern.equals(SCHEMA_DOCUMENT)) {
				OCMetadataDocumentCollection documentCollection = metadata
						.getDocuments();
				int colSZ = documentCollection.size();

				for (int z = 0; z < colSZ; z++) {
					OCDocumentMetadataObject dmo = documentCollection.get(z);
					lrs.createRowAndSetValues(new Object[] { null,
							SCHEMA_DOCUMENT, dmo.getName(), "TABLE",
							dmo.getComment(), null, null, null, null, null });
				}
			}
			if (schemaPattern == null || schemaPattern.equals(SCHEMA_CATALOG)) {
				OCMetadataCatalogCollection catalogCollection = metadata
						.getCatalogs();
				int colSZ = catalogCollection.size();

				for (int z = 0; z < colSZ; z++) {
					OCCatalogMetadataObject cmo = catalogCollection.get(z);
					lrs.createRowAndSetValues(new Object[] { null,
							SCHEMA_CATALOG, cmo.getName(), "TABLE",
							cmo.getComment(), null, null, null, null, null });
				}
			}
		} catch (JIException e) {
			throw new SQLException(e);
		}
		return lrs;
	}

	public ResultSet getSchemas() throws SQLException {
		LocalResultSet lrs = new LocalResultSet(connection);

		lrs.setHeader(1, "TABLE_SCHEM");
		lrs.setHeader(2, "TABLE_CATALOG");

		lrs.createRowAndSetValues(new Object[] { SCHEMA_DOCUMENT });
		lrs.createRowAndSetValues(new Object[] { SCHEMA_CATALOG });

		return lrs;
	}

	public ResultSet getCatalogs() throws SQLException {
		LocalResultSet lrs = LocalResultSet.createEmptyResultSet(
				new String[] { "TABLE_CAT" }, connection);
		return lrs;
	}

	public ResultSet getTableTypes() throws SQLException {
		LocalResultSet lrs = new LocalResultSet(connection);
		lrs.setHeader(1, "TABLE_TYPE");

		lrs.createRowAndSetValues(new Object[] { "TABLE" });
		return lrs;
	}

	public ResultSet getColumns(String catalog, String schemaPattern,
			String tableNamePattern, String columnNamePattern)
			throws SQLException {
		OCApp app = connection.getApplication();
		// LOG.info("catalog: "+catalog);
		// LOG.info("schemaPattern: "+schemaPattern);
		// LOG.info("tableNamePattern : "+tableNamePattern);
		// LOG.info("columnNamePattern: "+columnNamePattern);
		LocalResultSet lrs = LocalResultSet.createEmptyResultSet(new String[] {
				"TABLE_CAT",
				"TABLE_SCHEM",
				"TABLE_NAME",
				"COLUMN_NAME",
				"DATA_TYPE", // java.sql.Types
				"TYPE_NAME", "COLUMN_SIZE", "BUFFER_LENGTH", "DECIMAL_DIGITS",
				"NUM_PREC_RADIX",
				"NULLABLE",
				"REMARKS",
				"COLUMN_DEF",
				"SQL_DATA_TYPE", // unused
				"SQL_DATETIME_SUB", // unused
				"CHAR_OCTET_LENGTH", "ORDINAL_POSITION", "IS_NULLABLE",
				"SCOPE_CATLOG", "SCOPE_SCHEMA", "SCOPE_TABLE",
				"SOURCE_DATA_TYPE", "IS_AUTOINCREMENT" }, connection);
		try {
			OCConfigurationMetadataObject metadata = app.getMetadata();
			if (schemaPattern == null || schemaPattern.equals(SCHEMA_DOCUMENT)) {
				OCMetadataDocumentCollection documentCollection = metadata
						.getDocuments();
				OCDocumentMetadataObject dmo = documentCollection
						.find(tableNamePattern);
				if (dmo != null) { // а вдруг если схема не задана объект ей не
									// принадлежит

					OCMetadataAttributeCollection attributeCollection = dmo
							.getAttributes();
					attribute2resultSet(attributeCollection, lrs,
							SCHEMA_DOCUMENT, tableNamePattern);

					int colSZ = attributeCollection.size();

					// Add NUMBER
					lrs.createRowAndSetValues(new Object[] { null, // TABLE_CAT
							SCHEMA_DOCUMENT, // TABLE_SCHEM
							tableNamePattern, // TABLE_NAME
							"Number", // COLUMN_NAME
							Types.VARCHAR, // DATA_TYPE
							"VARCHAR", // TYPE_NAME
							Integer.valueOf(dmo.getNumberLength()), // COLUMN_SIZE
							null, // BUFFER_LENGTH
							null, // DECIMAL_DIGITS
							Integer.valueOf(10), // NUM_PREC_RADIX
							Integer.valueOf(columnNullableUnknown), // NULLABLE
							null, // REMARKS
							null, // COLUMN_DEF
							null, // SQL_DATA_TYPE
							null, // SQL_DATETIME_SUB
							Integer.valueOf(dmo.getNumberLength()), // CHAR_OCTET_LENGTH
							colSZ + 1, // ORDINAL_POSITION
							"", // IS_NULLABLE
							null, // SCOPE_CATLOG
							null, // SCOPE_SCHEMA
							null, // SCOPE_TABLE
							null, // SOURCE_DATA_TYPE
							"YES" // IS_AUTOINCREMENT
					});

					// Add DATE
					lrs.createRowAndSetValues(new Object[] { null, // TABLE_CAT
							SCHEMA_DOCUMENT, // TABLE_SCHEM
							tableNamePattern, // TABLE_NAME
							"Date", // COLUMN_NAME
							Types.TIMESTAMP, // DATA_TYPE
							"TIMESTAMP", // TYPE_NAME
							null, // COLUMN_SIZE
							null, // BUFFER_LENGTH
							null, // DECIMAL_DIGITS
							Integer.valueOf(10), // NUM_PREC_RADIX
							Integer.valueOf(columnNullableUnknown), // NULLABLE
							null, // REMARKS
							null, // COLUMN_DEF
							null, // SQL_DATA_TYPE
							null, // SQL_DATETIME_SUB
							null, // CHAR_OCTET_LENGTH
							colSZ + 2, // ORDINAL_POSITION
							"", // IS_NULLABLE
							null, // SCOPE_CATLOG
							null, // SCOPE_SCHEMA
							null, // SCOPE_TABLE
							null, // SOURCE_DATA_TYPE
							"NO" // IS_AUTOINCREMENT
					});

					// Add Posted
					lrs.createRowAndSetValues(new Object[] { null, // TABLE_CAT
							SCHEMA_DOCUMENT, // TABLE_SCHEM
							tableNamePattern, // TABLE_NAME
							"Posted", // COLUMN_NAME
							Types.SMALLINT, // DATA_TYPE
							"SMALLINT", // TYPE_NAME
							null, // COLUMN_SIZE
							null, // BUFFER_LENGTH
							null, // DECIMAL_DIGITS
							Integer.valueOf(10), // NUM_PREC_RADIX
							Integer.valueOf(columnNullableUnknown), // NULLABLE
							null, // REMARKS
							null, // COLUMN_DEF
							null, // SQL_DATA_TYPE
							null, // SQL_DATETIME_SUB
							null, // CHAR_OCTET_LENGTH
							colSZ + 3, // ORDINAL_POSITION
							"", // IS_NULLABLE
							null, // SCOPE_CATLOG
							null, // SCOPE_SCHEMA
							null, // SCOPE_TABLE
							null, // SOURCE_DATA_TYPE
							"NO" // IS_AUTOINCREMENT
					});

					// Add DeletionMark
					lrs.createRowAndSetValues(new Object[] { null, // TABLE_CAT
							SCHEMA_DOCUMENT, // TABLE_SCHEM
							tableNamePattern, // TABLE_NAME
							"DeletionMark", // COLUMN_NAME
							Types.SMALLINT, // DATA_TYPE
							"SMALLINT", // TYPE_NAME
							null, // COLUMN_SIZE
							null, // BUFFER_LENGTH
							null, // DECIMAL_DIGITS
							Integer.valueOf(10), // NUM_PREC_RADIX
							Integer.valueOf(columnNullableUnknown), // NULLABLE
							null, // REMARKS
							null, // COLUMN_DEF
							null, // SQL_DATA_TYPE
							null, // SQL_DATETIME_SUB
							null, // CHAR_OCTET_LENGTH
							colSZ + 4, // ORDINAL_POSITION
							"", // IS_NULLABLE
							null, // SCOPE_CATLOG
							null, // SCOPE_SCHEMA
							null, // SCOPE_TABLE
							null, // SOURCE_DATA_TYPE
							"NO" // IS_AUTOINCREMENT
					});
				}
			}
			if (schemaPattern == null || schemaPattern.equals(SCHEMA_CATALOG)) {
				OCMetadataCatalogCollection catalogCollection = metadata
						.getCatalogs();
				OCCatalogMetadataObject cmo = catalogCollection
						.find(tableNamePattern);
				if (cmo != null) {

					OCMetadataAttributeCollection mac = cmo.getAttributes();
					attribute2resultSet(mac, lrs, SCHEMA_CATALOG,
							tableNamePattern);

					int colSZ = catalogCollection.size();

					// add Code
					lrs.createRowAndSetValues(new Object[] { null, // TABLE_CAT
							SCHEMA_CATALOG, // TABLE_SCHEM
							tableNamePattern, // TABLE_NAME
							"Code", // COLUMN_NAME
							Types.VARCHAR, // DATA_TYPE
							"VARCHAR", // TYPE_NAME
							cmo.getCodeLength(), // COLUMN_SIZE
							null, // BUFFER_LENGTH
							null, // DECIMAL_DIGITS
							Integer.valueOf(10), // NUM_PREC_RADIX
							Integer.valueOf(columnNullableUnknown), // NULLABLE
							null, // REMARKS
							null, // COLUMN_DEF
							null, // SQL_DATA_TYPE
							null, // SQL_DATETIME_SUB
							null, // CHAR_OCTET_LENGTH
							colSZ + 1, // ORDINAL_POSITION
							"", // IS_NULLABLE
							null, // SCOPE_CATLOG
							null, // SCOPE_SCHEMA
							null, // SCOPE_TABLE
							null, // SOURCE_DATA_TYPE
							"YES" // IS_AUTOINCREMENT
					});
				}
			}
		} catch (JIException e) {
			throw new SQLException(e);
		}
		return lrs;
	}

	private static void attribute2resultSet(
			final OCMetadataAttributeCollection attributeCollection,
			LocalResultSet lrs, String schema, String tableName)
			throws SQLException, JIException {
		OCAttributeMetadataObject amo = null;
		OCTypeDescription typeDescription = null;
		int attrSZ = attributeCollection.size();
		for (int z = 0; z < attrSZ; z++) {
			amo = attributeCollection.get(z);
			typeDescription = amo.getTypeDescription();
			OCType ot = typeDescription.getType();
			int innerType = ot.getTypeCode();
			// determinate column size
			Integer columnSize = null;
			Integer decimalDigits = null;
			if (!(typeDescription.isMultiType())) {
				switch (innerType) {
					case OCType.OCT_STRING: {
						columnSize = typeDescription.getStringQualifiers()
								.getLength();
						break;
					}
					case OCType.OCT_NUMBER: {
						OCNumberQualifiers qualifiers = typeDescription
								.getNumberQualifiers();
						columnSize = qualifiers.getDigits();
						decimalDigits = qualifiers.getFractionDigits();
						break;
					}
					default: {
						columnSize = null;
						decimalDigits = null;
					}
				}
			}
			// determinate char_octet_length
			Integer charOctetLength = null;
			if (columnSize != null && innerType == OCType.OCT_STRING) {
				charOctetLength = columnSize;
			}

			OCType[] dataType = typeDescription.getNotNullTypes();

			lrs.createRowAndSetValues(new Object[] {
					null, // TABLE_CAT
					schema, // TABLE_SCHEM
					tableName, // TABLE_NAME
					amo.getName(), // COLUMN_NAME
					Integer.valueOf(OCType.typesToSQLType(dataType)), // DATA_TYPE
					OCType.typesToSQLTypeName(dataType), // typeDescription.isMultiType()?"MultiType":typeDescription.getType().getSynonym(),
															// // TYPE_NAME
					columnSize, // COLUMN_SIZE
					null, // BUFFER_LENGTH
					decimalDigits, // DECIMAL_DIGITS
					Integer.valueOf(10), // NUM_PREC_RADIX
					Integer.valueOf(columnNullableUnknown), // NULLABLE
					amo.getComment(), // REMARKS
					null, // COLUMN_DEF
					null, // SQL_DATA_TYPE - unused
					null, // SQL_DATETIME_SUB - unused
					charOctetLength, // CHAR_OCTET_LENGTH
					Integer.valueOf(z + 1), // ORDINAL_POSITION
					"", // IS_NULLABLE empty string --- if it cannot be
						// determined, else YES\NO

					// JDBC 3.0

					null, // SCOPE_CATLOG
					innerType > 100 ? typeDescription.getType().getLinkType()
							: null, // SCOPE_SCHEMA
					null, // SCOPE_TABLE
					null, // SOURCE_DATA_TYPE
					"" // IS_AUTOINCREMENT empty string --- if it cannot be
						// determined, else YES\NO
			});
		}
	}

	public ResultSet getColumnPrivileges(String paramString1,
			String paramString2, String paramString3, String paramString4)
			throws SQLException {
		LocalResultSet lrs = LocalResultSet
				.createEmptyResultSet(new String[] { "TABLE_CAT",
						"TABLE_SCHEM", "TABLE_NAME", "COLUMN_NAME", "GRANTOR",
						"GRANTEE", "PRIVILEGE", "IS_GRANTABLE" }, connection);
		return lrs;
	}

	public ResultSet getTablePrivileges(String paramString1,
			String paramString2, String paramString3) throws SQLException {
		LocalResultSet lrs = LocalResultSet.createEmptyResultSet(new String[] {
				"TABLE_CAT", "TABLE_SCHEM", "TABLE_NAME", "GRANTOR", "GRANTEE",
				"PRIVILEGE", "IS_GRANTABLE" }, connection);
		return lrs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#getBestRowIdentifier(java.lang.String,
	 * java.lang.String, java.lang.String, int, boolean)
	 */

	public ResultSet getBestRowIdentifier(String paramString1,
			String paramString2, String paramString3, int paramInt,
			boolean paramBoolean) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#getVersionColumns(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */

	public ResultSet getVersionColumns(String paramString1,
			String paramString2, String paramString3) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultSet getPrimaryKeys(String catalog, String schema, String table)
			throws SQLException {
		List<String> schemas = new ArrayList<String>();
		if (schema != null) {
			schemas.add(schema);
		} else {
			OCApp app = connection.getApplication();
			OCConfigurationMetadataObject configMetadata = null;
			try {
				configMetadata = app.getMetadata();
				OCObject metaObject = null;

				// findByFullName(SCHEMA_CATALOG+"."+table);
				metaObject = configMetadata.getCatalogs().find(table);
				if (metaObject != null) {
					schemas.add(SCHEMA_CATALOG);
				}

				// findByFullName(SCHEMA_DOCUMENT+"."+table);
				metaObject = configMetadata.getDocuments().find(table);
				if (metaObject != null) {
					schemas.add(SCHEMA_DOCUMENT);
				}
			} catch (JIException e) {
				throw new SQLException(e);
			}
		}
		LocalResultSet lrs = LocalResultSet.createEmptyResultSet(new String[] {
				"TABLE_CAT", "TABLE_SCHEM", "TABLE_NAME", "COLUMN_NAME",
				"KEY_SEQ", "PK_NAME" }, connection);

		for (String cName : schemas) {
			String columnName = null;
			boolean schemaReady = false;
			if (cName.equals(SCHEMA_CATALOG)) {
				columnName = "Code";
				schemaReady = true;
			} else if (cName.equals(SCHEMA_DOCUMENT)) {
				columnName = "Number";
				schemaReady = true;
			}
			if (schemaReady) {
				lrs.createRowAndSetValues(null, cName, table, columnName,
						Short.valueOf((short) 1), "PK_" + table);
				schemaReady = false;
			}
		}
		return lrs;
	}

	public ResultSet getImportedKeys(String paramString1, String paramString2,
			String paramString3) throws SQLException {
		// also void resultset
		return getExportedKeys(paramString1, paramString2, paramString3);
	}

	public ResultSet getExportedKeys(String paramString1, String paramString2,
			String paramString3) throws SQLException {
		LocalResultSet lrs = LocalResultSet.createEmptyResultSet(new String[] {
				"PKTABLE_CAT", "PKTABLE_SCHEM", "PKTABLE_NAME",
				"PKCOLUMN_NAME", "FKTABLE_CAT", "FKTABLE_SCHEM",
				"FKTABLE_NAME", "FKCOLUMN_NAME", "KEY_SEQ", "UPDATE_RULE",
				"DELETE_RULE", "FK_NAME", "PK_NAME", "DEFERRABILITY" },
				connection);
		return lrs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#getCrossReference(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */

	public ResultSet getCrossReference(String paramString1,
			String paramString2, String paramString3, String paramString4,
			String paramString5, String paramString6) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultSet getTypeInfo() throws SQLException {
		LOG.info("Getting type info");
		LocalResultSet lrs = LocalResultSet.createEmptyResultSet(new String[] {
				"TYPE_NAME", "DATA_TYPE", "PRECISION", "LITERAL_PREFIX",
				"LITERAL_SUFFIX", "CREATE_PARAMS", "NULLABLE",
				"CASE_SENSITIVE", "SEARCHABLE", "UNSIGNED_ATTRIBUTE",
				"FIXED_PREC_SCALE", "AUTO_INCREMENT", "LOCAL_TYPE_NAME",
				"MINIMUM_SCALE", "MAXIMUM_SCALE", "SQL_DATA_TYPE", // unused
				"SQL_DATETIME_SUB", // unused
				"NUM_PREC_RADIX"

		}, connection);
		try {
			OCApp application = connection.getApplication();
			// =============
			OCConfigurationMetadataObject config = application.getMetadata();
			OCMetadataDocumentCollection dc = config.getDocuments();
			OCDocumentMetadataObject dmo = null;
			int sz = dc.size();
			for (int i = 0; i < sz; i++) {
				dmo = dc.get(i);
				String typeName = dmo.getFullName();
				lrs.createRowAndSetValues(new Object[] { typeName, // TYPE_NAME
						Integer.valueOf(Types.STRUCT), // DATA_TYPE
						Integer.valueOf(0), // PRECISION
						null, // LITERAL_PREFIX
						null, // LITERAL_SUFFIX
						null, // CREATE_PARAMS
						Integer.valueOf(typeNullableUnknown), // NULLABLE
						Boolean.valueOf(false), // CASE_SENSITIVE
						Boolean.valueOf(true), // SEARCHABLE
						Boolean.valueOf(false), // UNSIGNED_ATTRIBUTE
						Boolean.valueOf(false), // FIXED_PREC_SCALE
						Boolean.valueOf(false), // AUTO_INCREMENT
						typeName, // LOCAL_TYPE_NAME
						Short.valueOf((short) 0), // MINIMUM_SCALE
						Short.valueOf((short) 0), // MAXIMUM_SCALE
						null, // SQL_DATA_TYPE - unused
						null, // SQL_DATETIME_SUB - unused
						Integer.valueOf(10) // NUM_PREC_RADIX
				});
			}

			OCMetadataCatalogCollection cc = config.getCatalogs();
			OCCatalogMetadataObject cmo = null;
			sz = cc.size();
			for (int i = 0; i < sz; i++) {
				cmo = cc.get(i);
				String typeName = cmo.getFullName();
				lrs.createRowAndSetValues(new Object[] { typeName, // TYPE_NAME
						Integer.valueOf(Types.STRUCT), // DATA_TYPE
						Integer.valueOf(0), // PRECISION
						null, // LITERAL_PREFIX
						null, // LITERAL_SUFFIX
						null, // CREATE_PARAMS
						Integer.valueOf(typeNullableUnknown), // NULLABLE
						Boolean.valueOf(false), // CASE_SENSITIVE
						Boolean.valueOf(true), // SEARCHABLE
						Boolean.valueOf(false), // UNSIGNED_ATTRIBUTE
						Boolean.valueOf(false), // FIXED_PREC_SCALE
						Boolean.valueOf(false), // AUTO_INCREMENT
						typeName, // LOCAL_TYPE_NAME
						Short.valueOf((short) 0), // MINIMUM_SCALE
						Short.valueOf((short) 0), // MAXIMUM_SCALE
						null, // SQL_DATA_TYPE - unused
						null, // SQL_DATETIME_SUB - unused
						Integer.valueOf(10) // NUM_PREC_RADIX
				});
			}
			// =============
			/*
			 * OCXDTOFactory factory = application.getXDTOFactory();
			 * OCXDTOPackageCollection pacCollection = factory.getPackages();
			 * OCXDTOPackage pac = null; int pacCol_SZ = pacCollection.size();
			 * String current_conf_uri = null; for(int z=0; z<pacCol_SZ; z++){
			 * pac = pacCollection.getPackage(z); String curUri =
			 * pac.getNamespaceURI(); if(curUri.endsWith("current-config")){
			 * current_conf_uri = curUri; break; } } if(current_conf_uri ==
			 * null) // текущая схема ненайдена\недоступна return lrs;
			 * 
			 * OCXMLSchemaSet schemaSet =
			 * factory.exportXMLSchema(current_conf_uri); if(schemaSet.size() ==
			 * 0) // текущая схема не содержит объектов return lrs;
			 * 
			 * OCXMLSchema schema = schemaSet.getSchema(0);
			 * 
			 * OCDOMDocument document = schema.getDOMDocument();
			 * 
			 * OCXMLWriter xmlWriter = application.newXMLWriter();
			 * xmlWriter.setString("UTF-8");
			 * 
			 * OCDOMWriter domWriter = application.newDOMWriter();
			 * domWriter.write(document, xmlWriter);
			 * 
			 * String res = xmlWriter.close();
			 * 
			 * NamespaceContext namespaceContext = new NamespaceContext() {
			 * 
			 * @SuppressWarnings("rawtypes") public Iterator getPrefixes(String
			 * namespaceURI) { throw new UnsupportedOperationException(); }
			 * 
			 * public String getPrefix(String namespaceURI) { throw new
			 * UnsupportedOperationException(); }
			 * 
			 * public String getNamespaceURI(String prefix) { if (prefix ==
			 * null) throw new NullPointerException("Null prefix"); else
			 * if("xsd".equals(prefix)) return
			 * "http://www.w3.org/2001/XMLSchema"; return
			 * XMLConstants.NULL_NS_URI; } }; Document w3cDocument =
			 * Utils.xml2Document(res);
			 * 
			 * XPath xpath = OCEDatabaseMetaData.factory.newXPath();
			 * xpath.setNamespaceContext(namespaceContext); XPathExpression expr
			 * = xpath.compile("/xsd:schema/xsd:simpleType/@name");
			 * 
			 * 
			 * Object result = expr.evaluate(w3cDocument,
			 * XPathConstants.NODESET);
			 * 
			 * NodeList nodes = (NodeList) result;
			 * 
			 * for (int i = 0; i < nodes.getLength(); i++) { String typeName =
			 * nodes.item(i).getNodeValue(); lrs.createRowAndSetValues(new
			 * Object[]{ typeName, // TYPE_NAME new Integer(Types.JAVA_OBJECT),
			 * //DATA_TYPE new Integer(0), // PRECISION null, // LITERAL_PREFIX
			 * null, // LITERAL_SUFFIX null, // CREATE_PARAMS new
			 * Integer(typeNullableUnknown), // NULLABLE new Boolean(false), //
			 * CASE_SENSITIVE new Boolean(true), // SEARCHABLE new
			 * Boolean(false), // UNSIGNED_ATTRIBUTE new Boolean(false), //
			 * FIXED_PREC_SCALE new Boolean(false), // AUTO_INCREMENT typeName,
			 * // LOCAL_TYPE_NAME new Short((short) 0), // MINIMUM_SCALE new
			 * Short((short) 0), // MAXIMUM_SCALE null, // SQL_DATA_TYPE -
			 * unused null, // SQL_DATETIME_SUB - unused new Integer(10) //
			 * NUM_PREC_RADIX }); }
			 */

		} catch (JIException jie) {
			throw new SQLException(jie);
		} /*
		 * catch (ParserConfigurationException e) { // do nothing
		 * e.printStackTrace(); } catch (SAXException e) { e.printStackTrace();
		 * } catch (IOException e) { e.printStackTrace(); } catch
		 * (XPathExpressionException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		return lrs;
	}

	public ResultSet getIndexInfo(String paramString1, String paramString2,
			String paramString3, boolean paramBoolean1, boolean paramBoolean2)
			throws SQLException {
		LocalResultSet lrs = LocalResultSet.createEmptyResultSet(new String[] {
				"TABLE_CAT", "TABLE_SCHEM", "TABLE_NAME", "NON_UNIQUE",
				"INDEX_QUALIFIER", "INDEX_NAME", "TYPE", "ORDINAL_POSITION",
				"COLUMN_NAME", "ASC_OR_DESC", "CARDINALITY", "PAGES",
				"FILTER_CONDITION" }, connection);
		return lrs;
	}

	public boolean supportsResultSetType(int paramInt) throws SQLException {
		return (paramInt == ResultSet.TYPE_FORWARD_ONLY);
	}

	public boolean supportsResultSetConcurrency(int paramInt1, int paramInt2)
			throws SQLException {
		return supportsResultSetType(paramInt1)
				&& (paramInt2 == ResultSet.CONCUR_READ_ONLY);
	}

	public boolean ownUpdatesAreVisible(int paramInt) throws SQLException {
		return supportsResultSetType(paramInt); // единственное требование
	}

	public boolean ownDeletesAreVisible(int paramInt) throws SQLException {
		return supportsResultSetType(paramInt);
	}

	public boolean ownInsertsAreVisible(int paramInt) throws SQLException {
		return supportsResultSetType(paramInt);
	}

	public boolean othersUpdatesAreVisible(int paramInt) throws SQLException {
		return false;
	}

	public boolean othersDeletesAreVisible(int paramInt) throws SQLException {
		return false;
	}

	public boolean othersInsertsAreVisible(int paramInt) throws SQLException {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#updatesAreDetected(int)
	 */

	public boolean updatesAreDetected(int paramInt) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#deletesAreDetected(int)
	 */

	public boolean deletesAreDetected(int paramInt) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#insertsAreDetected(int)
	 */

	public boolean insertsAreDetected(int paramInt) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#supportsBatchUpdates()
	 */

	public boolean supportsBatchUpdates() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#getUDTs(java.lang.String,
	 * java.lang.String, java.lang.String, int[])
	 */

	public ResultSet getUDTs(String paramString1, String paramString2,
			String paramString3, int[] paramArrayOfInt) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Connection getConnection() throws SQLException {
		return connection;
	}

	public boolean supportsSavepoints() throws SQLException {
		return false;
	}

	public boolean supportsNamedParameters() throws SQLException {
		return false; // only for callable statements
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#supportsMultipleOpenResults()
	 */

	public boolean supportsMultipleOpenResults() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#supportsGetGeneratedKeys()
	 */

	public boolean supportsGetGeneratedKeys() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#getSuperTypes(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */

	public ResultSet getSuperTypes(String paramString1, String paramString2,
			String paramString3) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#getSuperTables(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */

	public ResultSet getSuperTables(String paramString1, String paramString2,
			String paramString3) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#getAttributes(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */

	public ResultSet getAttributes(String paramString1, String paramString2,
			String paramString3, String paramString4) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean supportsResultSetHoldability(int paramInt)
			throws SQLException {

		return (paramInt == ResultSet.HOLD_CURSORS_OVER_COMMIT)
				|| (paramInt == ResultSet.CLOSE_CURSORS_AT_COMMIT);
	}

	public int getResultSetHoldability() throws SQLException {
		return ResultSet.HOLD_CURSORS_OVER_COMMIT;
	}

	public int getDatabaseMajorVersion() throws SQLException {
		return 1;
	}

	public int getDatabaseMinorVersion() throws SQLException {
		return 0;
	}

	public int getJDBCMajorVersion() throws SQLException {
		return connection.getDriver().getMajorVersion();
	}

	public int getJDBCMinorVersion() throws SQLException {
		return connection.getDriver().getMinorVersion();
	}

	public int getSQLStateType() throws SQLException {
		return 0; // need SQL_STATE_SQL99
	}

	public boolean locatorsUpdateCopy() throws SQLException {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#supportsStatementPooling()
	 */

	public boolean supportsStatementPooling() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#getRowIdLifetime()
	 */

	public RowIdLifetime getRowIdLifetime() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultSet getSchemas(String catalog, String schemaPattern)
			throws SQLException {
		return getSchemas();
	}

	public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
		return false;
	}

	public boolean autoCommitFailureClosesAllResultSets() throws SQLException {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#getClientInfoProperties()
	 */

	public ResultSet getClientInfoProperties() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#getFunctions(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */

	public ResultSet getFunctions(String paramString1, String paramString2,
			String paramString3) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.DatabaseMetaData#getFunctionColumns(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */

	public ResultSet getFunctionColumns(String paramString1,
			String paramString2, String paramString3, String paramString4)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

    public ResultSet getPseudoColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean generatedKeyAlwaysReturned() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
