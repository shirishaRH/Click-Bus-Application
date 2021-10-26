# Click-Bus-Application

checkout "click-bus-web-app"/"master" branch.
Make sure mongo server is up and running.
Db details : connect to localhost : 27017

API endpoints (Postman)
1. API to create a place (POST Request)
   URL : http://localhost:8080/createPlace  
   BODY :
   {
   "name": "",
   "slug": "",
   "city": "",
   "state" :""
   }     
   Sample Output:
   {"Message":"Place Created Successfully!”}   
   
   Else if place is already present with the same name:
   {"Message":"Place is already present with the name - pqr","Exception":""}
   

2. API to Edit a place (POST Request)  
   URL : http://localhost:8080/editPlace?name=abc
   BODY :
   {
   "name": "",
   "slug": "",
   "city": "",
   "state" :""
   }  
   Sample Output :
   If place found :
   {"Message":"Place Edited Successfully!"}

   Else if place not found:
   {"Message":"Place Not Found with the name - abc”}

   Else if place is already present with the same name :
   {"Message":"Place is already present with the name - abc”}


3. API to Get a specific place (GET Request)
   URL : http://localhost:8080/getPlace?name=abc
   Sample Output :
   {
     "data": 
       {
         "isCreated": true,
         "city": "",
         "name": "",
         "id": "",
         "state": "",
         "slug": ""
       } 
   }
       

4. API to List places and filter them by name (POST Request)
   URL : http://localhost:8080/listPlaces
   BODY : (if filter required)
   {
     "pageNo": 1,
     "pageSize": 25,
     "sort": "asc",
     "isFilter" : true,
     "filterValue" : ["", ""]
   }

   Else:
   {
     "pageNo": 1,
     "pageSize": 25,
     "sort": "asc",
     "isFilter" : false,
     "filterValue" : []
   }

   Sample Output:
   {
      "totalSize": 4,
      "data": [
                {
                  "city": "",
                  "name": "",
                  "state": "",
                  "slug": ""
                 }, 
                 {
                  "city": "",
                  "name": "",
                  "state": "",
                  "slug": ""
                 }
             ],
      "filteredSize": 2 
   }
