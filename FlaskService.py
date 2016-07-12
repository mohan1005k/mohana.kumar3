
from flask import Flask,jsonify,request
import json
from flask_sqlalchemy import SQLAlchemy
import dateutil.parser as parser
import Parser

import datetime
import time

import config


app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://'+config.user_name+':'+config.password+'@'+config.host_name+'/'+config.database_name
db = SQLAlchemy(app)


class loginformation(db.Model):
    idLogInformation = db.Column(db.Integer, primary_key=True)
    Url = db.Column(db.String(45))
    IpAddress = db.Column(db.String(45))
    ExecutionTime=db.Column(db.String(45))
    DateOfRequest=db.Column(db.String(100))
    HttpStatus=db.Column(db.String(45))
    Parameters=db.Column(db.String(300))
    RequestType=db.Column(db.String(300))


    def __init__(self, Parameters):
        self.Parameters = Parameters

    def __init__(self, IpAddress):
        self.IpAddress = IpAddress

    def __init__(self, ExecutionTime):
        self.ExecutionTime = ExecutionTime

    def __init__(self, HttpStatus):
        self.HttpStatus = HttpStatus

    def __init__(self, Url):
        self.Url = Url

    def __init__(self, DateOfRequest):
        self.DateOfRequest = DateOfRequest

    def __init__(self, idLogInformation):
        self.idLogInformation = idLogInformation

    def __init__(self, RequestType):
            self.RequestType = RequestType


def FetchResults(result):


    list = []
    dict2 = {}
    print result
    for row in result:
        print row
        dateOfRequest=(parser.parse(row.DateOfRequest))
        dict = {}
        dict["idLogInformation"]=row.idLogInformation
        date=str(dateOfRequest.isoformat())
        date=Parser.ReformatDate(date)
        dict["timestamp"] =date
        dict["url"]=row.Url
        dict["parameters"]=row.Parameters
        dict["request_duration_ms"]=row.ExecutionTime
        dict["response_code"]=int(row.HttpStatus)
        dict["request_ip_address"]=row.IpAddress
        dict["request_type"]=row.RequestType
        list.append(dict)

    dict2["data"]=list
    return jsonify(dict2)



@app.route('/api/auditLogs')
def fetch():
    limit = request.args.get('limit')
    offset= request.args.get('offset')
    if(limit is not None and (int(limit)>10 or int(limit)<0)):
        return request.form["absent"]
    startTime=request.args.get('startTime')
    endTime=request.args.get('endTime')
    if(startTime is not None and endTime is not None):
        startTime=Parser.getRequiredFormat(startTime)
        endTime=Parser.getRequiredFormat(endTime)
        result = loginformation.query.filter( loginformation.DateOfRequest>= startTime,loginformation.DateOfRequest<=endTime)
        return FetchResults(result)
    elif(not(startTime is None)):
        startTime = Parser.getRequiredFormat(startTime)
        result = loginformation.query.filter(loginformation.DateOfRequest >= startTime)
        return FetchResults(result)
    elif (not (endTime is None)):
        endTime = Parser.getRequiredFormat(endTime)
        result = loginformation.query.filter(loginformation.DateOfRequest <= endTime)
        return FetchResults(result)
    elif(limit is not None and offset is None):
        result = loginformation.query.limit(limit)
    elif(limit is None and offset is not None):
        result = loginformation.query.offset(offset)
    else:
        result = loginformation.query.order_by(loginformation.DateOfRequest.desc()).all()

    print (limit,offset)
    return FetchResults(result)
