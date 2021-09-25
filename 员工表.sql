create table employee(
	eid int auto_increment primary key,
	ename varchar(20),
	sex varchar(3),
	pid int,
	salary double(12,2),
	dizhi varchar(100),
	job varchar(20)
)default charset=utf8;
insert into employee values(default,'涂鏊飞','男',802,8000,'武汉市','销售经理',null);
insert into employee values(default,'胡梓卓','男',701,10000,'天门市','项目经理',null);
insert into employee values(default,'李小龙','男',702,9000,'厦门市','开发人员',null);
insert into employee values(default,'董文武','男',702,8500,'黑龙江','开发人员',null);

create table department(
	pid int primary key,
	pname varchar(50)
)default charset=utf8;
insert into department values(701,'研发部');
insert into department values(702,'市场部');
insert into department values(802,'销售部');

alter table employee add constraint fk_pid foreign key(pid) references department(pid);
#on delete cascade on update cascade;

select * from employee;
select * from department;