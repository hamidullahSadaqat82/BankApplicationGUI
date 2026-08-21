د دی پراجکټ لپاره باید اوریکل اسکیویل استعمال کړی. او که تاسی بل ډول دیټابیس استعمالو نو باید چی کنکشن یی هم په خپل لاس وکړی خو زه وړاندیز د اوریکل اسکیول کی
په دی ډیټا بیس کی باید په اندی ډول دری ټیبلونه موجود وی چی ما لاندی لیسټ کړی دی.
CUSTOMERINFORMATION
TABLE

MANAGERINFORMATION
TABLE

ADMININFORMATION
TABLE

په هر ټیبل کی باید داسی معلومات ولیکل شی او کالومونه او روګانی جوړی شی کومی چی زه په لاندی ډول تاسی ته لیسټ کوو نو شروع کوو د admininformation څخه
د اډمین انفارمیشن ټیبل باید په لاندی ډول ووسی

 Name					   Null?    Type
 ----------------------------------------- -------- ----------------------------
 ID					   NOT NULL NUMBER
 TID						    VARCHAR2(15)
 NAME						    VARCHAR2(15)
 PASSWORD					    VARCHAR2(300)


اوس تاسی باید له دی څخه ورسته د منیجر انفارمیشن ټیبل جوړ کړی. چی سټرکچر یی په لاندی ډول دی.
 Name					   Null?    Type
 ----------------------------------------- -------- ----------------------------
 ID					   NOT NULL NUMBER
 TID						    VARCHAR2(15)
 NAME						    VARCHAR2(30)
 FNAME						    VARCHAR2(30)
 PHONE						    VARCHAR2(12)
 PASSWORD					    VARCHAR2(15)
 CONTRY 					    VARCHAR2(30)
 PROVENCE					    VARCHAR2(30)
 STREET 					    VARCHAR2(15)

له دی څخه ورسته تاسی باید د کسټومر ټیبل جوړ کړی زه یی په لاندی ډول تاسی سره دکالمونو او روګانی سټرک چر شیر کوم.
 Name					   Null?    Type
 ----------------------------------------- -------- ----------------------------
 ID					   NOT NULL NUMBER
 TID						    VARCHAR2(15)
 NAME						    VARCHAR2(30)
 FNAME						    VARCHAR2(30)
 PHONE						    VARCHAR2(12)
 EMAIL						    VARCHAR2(40)
 PASSWORD					    VARCHAR2(300)
 CONTRY 					    VARCHAR2(20)
 PROVENCE					    VARCHAR2(20)
 STREET 					    VARCHAR2(30)
 BALANCE					    NUMBER(15,2)

