# -*- coding: utf-8 -*-
import datetime
import json
import requests
import pymysql.cursors
import logging
import sys

# log配置
logging.basicConfig(level=logging.INFO,
                    format='%(asctime)s %(filename)s[line:%(lineno)d] %(levelname)s  :%(message)s',
                    datefmt='%Y-%m-%d %H:%M:%S',
                    filename='/data/shell/logs/pyLog.log',
                    filemode='a')

# 数据库配置
config = {
    'host': 'localhost',
    'port': 3306,
    'user': 'root',
    'password': 'root',
    'database': 'test',
    'charset': 'utf8mb4',
    'cursorclass': pymysql.cursors.Cursor,
}

# 通用牌配置
url = 'http://www.bzj.com/ad2/uploadAdConfig.jhtml'
todaySql = "SELECT space3rdp ->> '$.key',spaceName,platformId,des FROM t_poly_3rdp_space_info WHERE (DATE_FORMAT(createTime,'%%Y-%%m-%%d') = '%s' OR DATE_FORMAT(updateTime,'%%Y-%%m-%%d') = '%s') and des is not null and des <> ''"

# 数据对象
class DataBean:
    def __init__(self):
        self.adUnitId = ''
        self.adName = ''
        self.platformid = "1"
        self.remark = ''

# 定义序列化
class UserEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, DataBean):
            return obj.__dict__
        return json.JSONEncoder.default(self, obj)

# 时间获取
def yesterday():
    today = datetime.date.today()
    one = datetime.timedelta(days=1)
    return today - one

# 发送数据
def send(url, data):
    python_to_json = json.dumps(data, cls=UserEncoder)
    payload = {'type': 1, 'data': python_to_json}
    responseEntity = requests.get(url, data=payload)
    logging.info("send data response:%s" % responseEntity.text)
    response = json.loads(responseEntity.text)
    return response['code']

# 获取数据
def getmysqldata(sql):
    spaceList = []
    connection = pymysql.connect(**config)
    try:
        with connection.cursor() as cursor:
            cursor.execute(sql)
            result = cursor.fetchall()
            for row in result:
                data = DataBean()
                data.adUnitId = row[0]
                data.adName = row[1]
                data.platformid = str(row[2])
                data.remark = row[3]
                spaceList.append(data)
        logging.info('get space data querySql: %s' % sql)
    except:
        info = sys.exc_info()
        logging.exception(info)
        connection.rollback()
    finally:
        connection.close()
    return spaceList


if __name__ == "__main__":
    yes = yesterday()
    sql = todaySql % (yes, yes)
    data = getmysqldata(sql)
    response = send(url, data)
    if response != 0:
        logging.info('send data success ')
    else:
        for code in (50001, 50001, 50001):
            if response == code:
                response = send(url, data)

    if response > 0:
        logging.error('send data fail Try again 3 times')