-- Copyright 2004-2023 H2 Group. Multiple-Licensed under the MPL 2.0,
-- and the EPL 1.0 (https://h2database.com/html/license.html).
-- Initial Developer: H2 Group
--

@reconnect off

SELECT ISOLATION_LEVEL FROM INFORMATION_SCHEMA.SESSIONS WHERE SESSION_ID = SESSION_ID();
>> READ COMMITTED

SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
> ok

SELECT ISOLATION_LEVEL FROM INFORMATION_SCHEMA.SESSIONS WHERE SESSION_ID = SESSION_ID();
>> READ UNCOMMITTED

SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
> ok

SELECT ISOLATION_LEVEL FROM INFORMATION_SCHEMA.SESSIONS WHERE SESSION_ID = SESSION_ID();
>> READ COMMITTED

SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
> ok

SELECT ISOLATION_LEVEL FROM INFORMATION_SCHEMA.SESSIONS WHERE SESSION_ID = SESSION_ID();
>> REPEATABLE READ

SET TRANSACTION ISOLATION LEVEL SNAPSHOT;
> ok

SELECT ISOLATION_LEVEL FROM INFORMATION_SCHEMA.SESSIONS WHERE SESSION_ID = SESSION_ID();
>> SNAPSHOT

SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
> ok

SELECT ISOLATION_LEVEL FROM INFORMATION_SCHEMA.SESSIONS WHERE SESSION_ID = SESSION_ID();
>> SERIALIZABLE

SET SESSION CHARACTERISTICS AS TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
> ok

SELECT ISOLATION_LEVEL FROM INFORMATION_SCHEMA.SESSIONS WHERE SESSION_ID = SESSION_ID();
>> READ UNCOMMITTED

SET SESSION CHARACTERISTICS AS TRANSACTION ISOLATION LEVEL READ COMMITTED;
> ok

SELECT ISOLATION_LEVEL FROM INFORMATION_SCHEMA.SESSIONS WHERE SESSION_ID = SESSION_ID();
>> READ COMMITTED

SET SESSION CHARACTERISTICS AS TRANSACTION ISOLATION LEVEL REPEATABLE READ;
> ok

SELECT ISOLATION_LEVEL FROM INFORMATION_SCHEMA.SESSIONS WHERE SESSION_ID = SESSION_ID();
>> REPEATABLE READ

SET SESSION CHARACTERISTICS AS TRANSACTION ISOLATION LEVEL SNAPSHOT;
> ok

SELECT ISOLATION_LEVEL FROM INFORMATION_SCHEMA.SESSIONS WHERE SESSION_ID = SESSION_ID();
>> SNAPSHOT

SET SESSION CHARACTERISTICS AS TRANSACTION ISOLATION LEVEL SERIALIZABLE;
> ok

SELECT ISOLATION_LEVEL FROM INFORMATION_SCHEMA.SESSIONS WHERE SESSION_ID = SESSION_ID();
>> SERIALIZABLE

SET SESSION CHARACTERISTICS AS TRANSACTION ISOLATION LEVEL READ COMMITTED;
> ok

SELECT SETTING_VALUE FROM INFORMATION_SCHEMA.SETTINGS WHERE SETTING_NAME = 'VARIABLE_BINARY';
>> FALSE

CREATE MEMORY TABLE TEST(B BINARY);
> ok

SCRIPT NODATA NOPASSWORDS NOSETTINGS NOVERSION TABLE TEST;
> SCRIPT
> --------------------------------------------------
> CREATE USER IF NOT EXISTS "SA" PASSWORD '' ADMIN;
> CREATE MEMORY TABLE "PUBLIC"."TEST"( "B" BINARY );
> -- 0 +/- SELECT COUNT(*) FROM PUBLIC.TEST;
> rows (ordered): 3

DROP TABLE TEST;
> ok

SET VARIABLE_BINARY TRUE;
> ok

SELECT SETTING_VALUE FROM INFORMATION_SCHEMA.SETTINGS WHERE SETTING_NAME = 'VARIABLE_BINARY';
>> TRUE

CREATE MEMORY TABLE TEST(B BINARY);
> ok

SCRIPT NODATA NOPASSWORDS NOSETTINGS NOVERSION TABLE TEST;
> SCRIPT
> ----------------------------------------------------------
> CREATE USER IF NOT EXISTS "SA" PASSWORD '' ADMIN;
> CREATE MEMORY TABLE "PUBLIC"."TEST"( "B" BINARY VARYING );
> -- 0 +/- SELECT COUNT(*) FROM PUBLIC.TEST;
> rows (ordered): 3

DROP TABLE TEST;
> ok

SET VARIABLE_BINARY FALSE;
> ok

SET LOCK_MODE 0;
> ok

CALL LOCK_MODE();
>> 0

SET LOCK_MODE 1;
> ok

CALL LOCK_MODE();
>> 3

SET LOCK_MODE 2;
> ok

CALL LOCK_MODE();
>> 3

SET LOCK_MODE 3;
> ok

CALL LOCK_MODE();
>> 3

@reconnect on

SELECT CURRENT_PATH;
> CURRENT_PATH
> ------------
>
> rows: 1

SET SCHEMA_SEARCH_PATH PUBLIC, INFORMATION_SCHEMA;
> ok

SELECT CURRENT_PATH;
>> "PUBLIC","INFORMATION_SCHEMA"

SET SCHEMA_SEARCH_PATH PUBLIC;
> ok

CREATE TABLE TEST(C1 INT, C2 INT);
> ok

CREATE INDEX IDX ON TEST(C1 ASC, C2 DESC);
> ok

SELECT COLUMN_NAME, ORDERING_SPECIFICATION, NULL_ORDERING FROM INFORMATION_SCHEMA.INDEX_COLUMNS
    WHERE INDEX_NAME = 'IDX';
> COLUMN_NAME ORDERING_SPECIFICATION NULL_ORDERING
> ----------- ---------------------- -------------
> C1          ASC                    FIRST
> C2          DESC                   LAST
> rows: 2

DROP INDEX IDX;
> ok

SET DEFAULT_NULL_ORDERING LOW;
> ok

CREATE INDEX IDX ON TEST(C1 ASC, C2 DESC);
> ok

SELECT COLUMN_NAME, ORDERING_SPECIFICATION, NULL_ORDERING FROM INFORMATION_SCHEMA.INDEX_COLUMNS
    WHERE INDEX_NAME = 'IDX';
> COLUMN_NAME ORDERING_SPECIFICATION NULL_ORDERING
> ----------- ---------------------- -------------
> C1          ASC                    FIRST
> C2          DESC                   LAST
> rows: 2

DROP INDEX IDX;
> ok

SET DEFAULT_NULL_ORDERING HIGH;
> ok

CREATE INDEX IDX ON TEST(C1 ASC, C2 DESC);
> ok

SELECT COLUMN_NAME, ORDERING_SPECIFICATION, NULL_ORDERING FROM INFORMATION_SCHEMA.INDEX_COLUMNS
    WHERE INDEX_NAME = 'IDX';
> COLUMN_NAME ORDERING_SPECIFICATION NULL_ORDERING
> ----------- ---------------------- -------------
> C1          ASC                    LAST
> C2          DESC                   FIRST
> rows: 2

DROP INDEX IDX;
> ok

SET DEFAULT_NULL_ORDERING FIRST;
> ok

CREATE INDEX IDX ON TEST(C1 ASC, C2 DESC);
> ok

SELECT COLUMN_NAME, ORDERING_SPECIFICATION, NULL_ORDERING FROM INFORMATION_SCHEMA.INDEX_COLUMNS
    WHERE INDEX_NAME = 'IDX';
> COLUMN_NAME ORDERING_SPECIFICATION NULL_ORDERING
> ----------- ---------------------- -------------
> C1          ASC                    FIRST
> C2          DESC                   FIRST
> rows: 2

DROP INDEX IDX;
> ok

SET DEFAULT_NULL_ORDERING LAST;
> ok

CREATE INDEX IDX ON TEST(C1 ASC, C2 DESC);
> ok

SELECT COLUMN_NAME, ORDERING_SPECIFICATION, NULL_ORDERING FROM INFORMATION_SCHEMA.INDEX_COLUMNS
    WHERE INDEX_NAME = 'IDX';
> COLUMN_NAME ORDERING_SPECIFICATION NULL_ORDERING
> ----------- ---------------------- -------------
> C1          ASC                    LAST
> C2          DESC                   LAST
> rows: 2

DROP INDEX IDX;
> ok

SET DEFAULT_NULL_ORDERING LOW;
> ok

DROP TABLE TEST;
> ok
