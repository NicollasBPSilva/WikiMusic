#Default Layout
Generated using [DbSchema](https://dbschema.com)




### Default Layout
![img](./DefaultLayout.svg)



### Table wikimusi.album 
| | | |
|---|---|---|
| * &#128273;  | id| INT AUTO_INCREMENT |
|  | nome| VARCHAR(50)  |
|  | ano\_lancamento| DATE  |
|  | quantidade\_musicas| SMALLINT  |


##### Indexes 
| | | |
|---|---|---|
| &#128273;  | pk\_album | ON id|

##### Options 
engine=InnoDB 


### Table wikimusi.grupo_banda 
| | | |
|---|---|---|
|  | albuns| VARCHAR(50)  |
| * &#128273;  | id| INT AUTO_INCREMENT |
|  | ano\_formacao| INT  |
|  | em\_atividade| BOOLEAN  |
|  | nome| VARCHAR(50)  |


##### Indexes 
| | | |
|---|---|---|
| &#128273;  | pk\_artistas | ON id|

##### Options 
engine=InnoDB 


### Table wikimusi.integrantes 
| | | |
|---|---|---|
| * &#128273;  | id| INT AUTO_INCREMENT |
|  | nome| VARCHAR(50)  |
|  | instrumento| VARCHAR(50)  |
|  | nacionalidade| VARCHAR(50)  |
|  | em\_atividade| BOOLEAN  |


##### Indexes 
| | | |
|---|---|---|
| &#128273;  | pk\_integrantes | ON id|

##### Options 
engine=InnoDB 


### Table wikimusi.musicas 
| | | |
|---|---|---|
| * &#128273;  | id| INT AUTO_INCREMENT |
|  | nome| VARCHAR(50)  |
|  | lancamento| DATE  |


##### Indexes 
| | | |
|---|---|---|
| &#128273;  | pk\_musicas | ON id|

##### Options 
engine=InnoDB 


### Table wikimusi.single 
| | | |
|---|---|---|
| * &#128273;  | id| INT AUTO_INCREMENT |
|  | nome| VARCHAR(50)  |


##### Indexes 
| | | |
|---|---|---|
| &#128273;  | pk\_single | ON id|

##### Options 
engine=InnoDB 



