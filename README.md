```$xslt

 _                _ _   _                _               _    
| |__   ___  __ _| | |_| |__         ___| |__   ___  ___| | __
| '_ \ / _ \/ _` | | __| '_ \ _____ / __| '_ \ / _ \/ __| |/ /
| | | |  __/ (_| | | |_| | | |_____| (__| | | |  __/ (__|   < 
|_| |_|\___|\__,_|_|\__|_| |_|      \___|_| |_|\___|\___|_|\_\
                                                              

```

## Introduction 
Health-check is built with the purpose of tesing your API which you can't figuratively all the time. It supports multi region testing. It is lock-free and practically free to deploy unless testing for more than ~100 API and over a million API calls a month.

It supports single step and multi-step api testing for your CRUD requirements. ``` To support logic based api testing.```  


## How to use 


1. you need have aws account access key and access secret. You can generate <a ref=here >here</a>.
2. you need to a config.properties

    ```a
    aws.access.key.id=
    aws.secret.access.key=
    influx.db.url=
    influx.db.name=
    dynamo.db.table=
    server.port=
    ```
3. you need to have a influx db setup to push all the metrics. You can refer <a ref=here >here</a>. It is free to use and will sufice most of your needs for testing. moving on you can setup it up with aws ec2.


## Service Architecture 

this is the overview of the service architecture.
Multi region testing is completely configurable and can be modified. It uses CloudWatch cron to trigger health-checks.

 
![Screenshot](img/Screenshot%202020-03-31%20at%2011.45.27%20PM.png)


## Current Dev

1. Single step API [![start with why](https://img.shields.io/badge/status-done-green)]()
2. Multi step API [![start with why](https://img.shields.io/badge/status-dev-blue)]()
3. multi DB configuration [![start with why](https://img.shields.io/badge/status-design-gray)]()
4. Multi region deployment [![start with why](https://img.shields.io/badge/status-dev-blue)]()


