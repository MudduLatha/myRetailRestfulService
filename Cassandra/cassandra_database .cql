
CREATE KEYSPACE IF NOT EXISTS myretail WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'} 
AND durable_writes = true;


CREATE TABLE IF NOT EXISTS myretail.productprices (
   	productID bigint,
   	price float,
    	currency text,
    	PRIMARY KEY (productID)
);


INSERT INTO myretail.productprices (productID, price, currency) VALUES(13860418,22.15,'USD');
INSERT INTO myretail.productprices (productID, price, currency) VALUES(13860419,88.93,'USD');
INSERT INTO myretail.productprices (productID, price, currency) VALUES(13860420,76.45,'USD');
INSERT INTO myretail.productprices (productID, price, currency) VALUES(13860421,13.27,'USD');
INSERT INTO myretail.productprices (productID, price, currency) VALUES(13860424,55.55,'USD');
INSERT INTO myretail.productprices (productID, price, currency) VALUES(13860428,6.15,'USD');
INSERT INTO myretail.productprices (productID, price, currency) VALUES(13860429,1.88,'USD');