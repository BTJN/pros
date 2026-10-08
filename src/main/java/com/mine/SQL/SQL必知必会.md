- mysqlsh
    - mysqlsh \c root@localhost:3306 -proot
    - \sql
    - \source path\to\yourfile.sql
CPU / IO / compute / memory

SQL {
    DDL : Data Definition Language (Create Delete Modify) Strucutures of Database and tables (we can finish the procedure without commit)
    DML : Data Manipulation Language (Create Delete Update)
    DCL : Data Control Language (access control and security levels)
    DQL : Data Query Language (Select)
} 
OLTP
OLAP
RDBMS

ERD(Entity Relationship Diagram) : entity property relationship

DBMS: Database management system = multiple database + management procedure
DataBase Patterns: Relational(Oracle MySQL PostgreSQL) Document(MongoDB) Search-Engine(Elasticsearch) Key-value(Redis) Wide-Column(Cassandra)
DBS : Database System -> Concept 

SELECT ... 
FROM ... 
WHERE ... 
GROUP BY ... 
HAVING ... 
ORDER BY ...

FROM > WHERE > GROUP BY > HAVING > SELECT's Fields > DISTINCT > ORDER BY > LIMIT

```sql
CREATE TABLE `player` (
    `player_id` int (11) NOT NULL AUTO_INCREMENT,
    `team_id` int (11) NOT NULL,
    `player_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `height` float (3, 2) NULL DEFAULT 0.00,
    PRIMARY KEY (`player_id`) USING BTREE,
    UNIQUE INDEX `player_name` (`player_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
```

# function
- arithmatic: 
    - abs() 
    - mod()
    - round()
- string
    - concat()
    - length()
    - char_length()
    - lower()
    - upper()
    - replace()
    - substring()
- date
    - current_date()
    - current_time()
    - current_timestamp()
    - extract()
    - date()
    - year()
    - month()
    - day()
    - hour()
    - minute()
    - second()
- transformat
    - cast() -> select cast(123.123 as int); select cast(123.123 as decimal(8, 2))
    - coalesce() -> select coalesce(null, 1, 2)
- cluster
    - count
    - max
    - min
    - sum
    - avg

# sub query
