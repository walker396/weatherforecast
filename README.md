# weatherforecast
weatherforecast


=========================================

### **Overview**



## Requirements
frontend:
react
axios
pubsubjs

backend:
jdk 1.8
springboot 2.4.2
redis latest
mysql 5.7
maven


## Instructions
##import DDL to mysql

-- complexica.itinerary definition

CREATE TABLE `itinerary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `status` varchar(2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



-- complexica.plan_detail definition

CREATE TABLE `plan_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city_id` varchar(50) NOT NULL,
  `city` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `weather_date` date DEFAULT NULL,
  `weather_time` varchar(15) DEFAULT NULL,
  `tempture` varchar(15) DEFAULT NULL,
  `weather` varchar(100) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `itinerary_id` bigint(20) DEFAULT NULL,
  `plan_details_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1ghco8voss1gtr8b9dldyqnn9` (`plan_details_id`),
  KEY `FKp1kr1t9wkuv6a4mutk2wjsma5` (`itinerary_id`),
  CONSTRAINT `FKp1kr1t9wkuv6a4mutk2wjsma5` FOREIGN KEY (`itinerary_id`) REFERENCES `itinerary` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=494 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-------------------
Run frontend service on the command:
 yarn start

Run Bankend service on the command:
mvn spring-boot:run






