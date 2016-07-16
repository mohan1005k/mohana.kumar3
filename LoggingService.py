import boto.sqs,json
import MySQLdb
import time


from sqlalchemy import *
import sqlalchemy
import sqlalchemy.ext.declarative
import sqlalchemy.orm.interfaces
import sqlalchemy.exc
import config




def initiateConnections():
    conn = boto.sqs.connect_to_region(config.region)
    my_queue = conn.get_queue(config.queue_name)


    conncetion_string = "mysql+pymysql://" + config.user_name + ":" +config.password + "@" + config.host_name + "/" + config.database_name
    engine = create_engine(conncetion_string)
    metadata = MetaData()
    connection = engine.connect()



def readFromQueueAndUpdate():

    #initiating connections
    conn = boto.sqs.connect_to_region(config.region)
    my_queue = conn.get_queue(config.queue_name)


    conncetion_string = "mysql+pymysql://" + config.user_name + ":" +config.password + "@" + config.host_name + "/" + config.database_name
    engine = create_engine(conncetion_string)
    metadata = MetaData()
    connection = engine.connect()

    #Polling from queue and printing
    while(my_queue.count()>0):
        print ('in loop:')
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
            logInformationTable = Table('loginformation', metadata, autoload=True, autoload_with=engine)
            ins = logInformationTable.insert().values(Url=url, Parameters=parameters,
                                                      HttpStatus=httpStatus,
                                                      IpAddress=ipAddress, DateOfRequest=dateOfRequest, ExecutionTime=executionTime)

            res = connection.execute(ins)
            rowDetails=[url,ipAddress,executionTime,dateOfRequest,httpStatus,parameters];
            print('row updated')
            my_queue.delete_message(row)

if __name__ == "__main__":

    readFromQueueAndUpdate()
