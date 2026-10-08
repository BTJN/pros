```sql
drop database demo;
create database demo;
create table demo.test (barcode text, goodsname text, price int);
-- goodsname barcode 都有可能重复 因此不能作为主键
alter table demo.test add column itemnumber int primary key auto_increment;
insert into demo.test (barcode, goodsname, price) values ('0001', '本', 3);

create table demo.goodsmaster (barcode text, goodsname text, price double, itemnumber int primary key auto_increment);
select * from demo.goodsmaster;
select sum(price) as total from demo.goodsmaster;
alter table demo.goodsmaster modify column price decimal(5, 2);
```

```sql
- 插入数据goodsmaster
INSERT INTO demo.goodsmaster
(
barcode,
goodsname,
price
)
VALUES
(
'0001',
'书',
0.47
);
-- 第二条
INSERT INTO demo.goodsmaster
(
barcode,
goodsname,
price
)
VALUES
(
'0002',
'笔',
0.44
);
-- 第三条
INSERT INTO demo.goodsmaster
(
barcode,
goodsname,
price
)
VALUES
(
'0002',
'胶水',
0.19
);
```

1. MySQL 数据库的连接方式配置
    - 网络通讯协议（TCP/IP）
    - 命名管道（Named Pipe）
    - 共享内存（Shared Memory）
2. 项目的实际需求 --> 解决问题所需的知识点 --> 用好这些知识的实战经验
3. 数据存储过程总共有 4 步，分别是创建数据库、确认字段、创建数据表、插入数据
4. information_schema、performance_schema、sys、mysql 作用
    1. “information_schema”是 MySQL 系统自带的数据库，主要保存 MySQL 数据库服务器的系统信息，比如数据库的名称、
        数据表的名称、字段名称、存取权限、数据文件所在的文件夹和系统使用的文件夹，等等
    2. “performance_schema”是 MySQL 系统自带的数据库，可以用来监控 MySQL 的各类性能指标
    3. “sys”数据库是 MySQL 系统自带的数据库，主要作用是，以一种更容易被理解的方式展示 MySQL 
        数据库服务器的各类性能指标，帮助系统管理员和开发人员监控 MySQL的技术性能
    4. “mysql”数据库保存了 MySQL 数据库服务器运行时需要的系统信息，比如数据文件夹、当前使用的字符集、约束检查信息，等等
5. Field Type Null Key Default Extra
6. 主键可以确保数据的唯一性，而且能够减少数据错误
    - 必须可以唯一标识数据表中的记录;
    - 不能是空；
    - 必须唯一，不能重复
    
| 类型 | 有符号数取值范围 | 无符号数取值范围 | 占用字节数 | 适用场景 |
| :---: | :---: | :---: | :---: | :---: |
| TINYINT | -128~127 | 0~255 | 1 | 一般用于枚举数据，比如系统设定等取值范围很小且固定的场景 |
| SMALLINT | -32768~32767 | 0~65535 | 2 | 可以用于较小范围的统计数据，比如统计工厂的固定资产库存数量等 |
| MEDIUMINT | -8388608~8388607 | 0~16777215 | 3 | 用于较大整数的计算，比如车站每日的客流量等 |
| INT (INTEGER) | -2147483648~2147483647 | 0~4294967295 | 4 | 取值范围足够大，一般情况下不用考虑超限问题，用得最多 |
| BIGINT | -9223372036854775808~9223372036854775807 | 0~18446744073709551615 | 8 | 只有当你处理特别巨大的整数时才会用到。比如双十一的交易量、大型门户网站点击量、证券公司衍生产品持仓等 |

## 在评估用哪种整数类型的时候，你需要考虑存储空间和可靠性的平衡问题
- 在实际工作中，系统故障产生的成本远远超过增加几个字段存储空间所产生的成本。
  因此，我建议你首先确保数据不会超过取值范围，在这个前提之下，再去考虑如何节省存储空间。

| 类型 | 有符号数取值范围 | 无符号数取值范围 | 占用字节数 |
| :---: | :--- | :--- | :---: |
| FLOAT | (-3.402823466E+38, -1.175494351E-38), 0,<br>(1.175494351 E-38, 3.402823466351 E+38) | 0, (1.175494351 E-38,<br>3.402823466 E+38) | 4 |
| DOUBLE | (-1.7976931348623157E+308, -<br>2.2250738585072014E-308), 0,<br>(2.2250738585072014E-308,<br>1.7976931348623157E+308) | 0, (2.2250738585072014E-308,<br>1.7976931348623157E+308) | 8 |

- 浮点数类型有个缺陷，就是不精准

7. MySQL 对浮点类型数据的存储方式上
    - MySQL 用 4 个字节存储 FLOAT 类型数据，用 8 个字节来存储 DOUBLE 类型数据。
    无论哪个，都是采用二进制的方式来进行存储的。比如 9.625，用二进制来表达，就是
    1001.101，或者表达成 1.001101×2^3。看到了吗？如果尾数不是 0 或 5（比如9.624），
    你就无法用一个二进制数来精确表达。怎么办呢？就只好在取值允许的范围内进行近似（四舍五入）。
8. MySQL 有没有精准的数据类型呢？当然有，这就是定点数类型：DECIMAL
    - MySQL 用 DECIMAL（M,D）的方式表示高精度小数。其中，M 
      表示整数部分加小数部分，一共有多少位，M<=65。D 表示小数部分位数，D<M
    