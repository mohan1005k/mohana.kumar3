import boto.sqs,json
import MySQLdb
import time


from sqlalchemy import *
import sqlalchemy
import sqlalchemy.ext.declarative
import sqlalchemy.orm.interfaces
import sqlalchemy.exc
import config

conn = boto.sqs.connect_to_region("us-east-1")
my_queue = conn.get_queue('cnu2016_mohana_kumar_assignment05')


conncetion_string = "mysql+pymysql://" + config.user_name + ":" +config.password + "@" + config.host_name + "/" + config.database_name
engine = create_engine(conncetion_string)
metadata = MetaData()
connection = engine.connect()



def readFromQueueAndUpdate():


    while(my_queue.count()>0):

        time.sleep(5)
        row=my_queue.read()
        if(row):
            body = row.get_body()
            body = json.loads(body)
            parameters=body['parameters']
            parameters=parameters.encode('utf-8')
            httpStatus=body['httpStatus']
            url=body['url']
            ipAddress=body['ipAdress']
            executionTime=body['executionTime']
            dateOfRequest=body['dateOfRequest']
            logInformationTable = Table('LogInformation', metadata, autoload=True, autoload_with=engine)
            ins = logInformationTable.insert().values(Url=url, Parameters=parameters,
                                                      HttpStatus=httpStatus,
                                                      IpAddress=ipAddress, DateOfRequest=dateOfRequest, ExecutionTime=executionTime)

            res = connection.execute(ins)
            rowDetails=[url,ipAddress,executionTime,dateOfRequest,httpStatus,parameters];
            print('row updated')
        else:
            break
        my_queue.delete_message(row)


readFromQueueAndUpdate()
