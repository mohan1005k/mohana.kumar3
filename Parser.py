def findMonth(month):
    if(month is 1):
        return "Jan"
    if(month is 2):
        return "Feb"
    if (month is 3):
        return "Mar"
    if (month is 4):
        return "Apr"
    if (month is 5):
        return "May"
    if (month is 6):
        return "Jun"
    if (month is 7):
        return "Jul"
    if (month is 8):
        return "Aug"
    if (month is 9):
        return "Sep"
    if (month is 10):
        return "Oct"
    if( month is 11):
        return "Nov"
    if(month is 12):
        return "Dec"

def ReformatDate(date):
    return date[5]+date[6]+"/"+date[8]+date[9]+"/"+date[0]+date[1]+date[2]+date[3]+date[10:]

def getRequiredFormat(time1):
    day = time1[3] + time1[4]
    month = int(time1[0] + time1[1])
    month = findMonth(month)
    year = time1[6] + time1[7] + time1[8] + time1[9]

    hour = int(time1[11] + time1[12])
    min = time1[14] + time1[15]
    sec = time1[17] + time1[18]

    dayOrNight = "AM"
    if (hour > 12):
        dayOrNight = "PM"
        hour = hour - 12

    hour = str(hour)
    resultDate = month + " " + day + ", " + year + " " + hour + ":" + min + ":" + sec + " " + dayOrNight
    return resultDate
