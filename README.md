<h2>Articles used in tutorial<h2>
<ol>
<li> Download <br>
https://www.mongodb.com/download-center/community </li>
<li> Install/Configure/Startup <br>
https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/ </li>
<li> Working with POJO <br>
https://mongodb.github.io/mongo-java-driver/3.6/driver/getting-started/quick-start-pojo/ </li>
<li> Mongo and Spring data <br>
https://spring.io/guides/gs/accessing-data-mongodb/ </li>
</ol>

<h2>How to use tutorial</h2>
<ul>
<li>To check how to work with mongo collection refer to ua.vlpo.custom.samples.Main</li>
<li>To check how to work with mongo collection and get result serialized to custom POJO refer to ua.vlpo.custom.samples.SerializedMain</li>
<li>Interested in spring data? Refer to ua.vlpo.MongoInteractionBootApplication</li>
</ul>

<h2>How to start mongo single instance or replica set</h2>
<h5>Single instance</h5>
<ul>
<li>mongo installation path <br>
D:\mongodb-4.0.10 </li>

<li>mongo start (single node) <br>
D:\mongodb-4.0.10\bin>mongod.exe --dbpath=D:\mongodb-4.0.10\data\db
</ul>

<h5>Replica set</h5>
<ul>
<li>mongo start (replica set) - in separate consoles <br>
D:\mongodb-4.0.10\bin> <br>
mongod.exe --port 27017 --dbpath=D:\mongodb-4.0.10\data\db --replSet my-replica-set <br>
D:\mongodb-4.0.10\bin> <br>
mongod.exe --port 27018 --dbpath=D:\mongodb-4.0.10\data\db1 --replSet my-replica-set <br>
D:\mongodb-4.0.10\bin> <br>
mongod.exe --port 27019 --dbpath=D:\mongodb-4.0.10\data\db2 --replSet my-replica-set  </li>

<li>Run (in separate console) <br>
D:\mongodb-4.0.10\bin>mongo.exe  </li>

<li>type (in mongo.exe client) <br>
rs.initiate() <br>
 <br>
prompt will change to: <br>
my-replica-set:PRIMARY>  </li>

<li>To add members type: <br>
my-replica-set:PRIMARY> rs.add("localhost:27018") <br>
my-replica-set:PRIMARY> rs.add("localhost:27019") </li>

<li>to get replica set details type: <br>
my-replica-set:PRIMARY> rs.status() </li>


<li>Response which you will get after submitting command <br>
<pre>
{
        "set" : "my-replica-set",
        "date" : ISODate("2019-07-05T13:27:05.965Z"),
        "myState" : 1,
        "term" : NumberLong(1),
        "syncingTo" : "",
        "syncSourceHost" : "",
        "syncSourceId" : -1,
        "heartbeatIntervalMillis" : NumberLong(2000),
        "optimes" : {
                "lastCommittedOpTime" : {
                        "ts" : Timestamp(1562333216, 1),
                        "t" : NumberLong(1)
                },
                "readConcernMajorityOpTime" : {
                        "ts" : Timestamp(1562333216, 1),
                        "t" : NumberLong(1)
                },
                "appliedOpTime" : {
                        "ts" : Timestamp(1562333216, 1),
                        "t" : NumberLong(1)
                },
                "durableOpTime" : {
                        "ts" : Timestamp(1562333216, 1),
                        "t" : NumberLong(1)
                }
        },
        "lastStableCheckpointTimestamp" : Timestamp(1562333216, 1),
        "members" : [
                {
                        "_id" : 0,
                        "name" : "localhost:27017",
                        "health" : 1,
                        "state" : 1,
                        "stateStr" : "PRIMARY",
                        "uptime" : 169,
                        "optime" : {
                                "ts" : Timestamp(1562333216, 1),
                                "t" : NumberLong(1)
                        },
                        "optimeDate" : ISODate("2019-07-05T13:26:56Z"),
                        "syncingTo" : "",
                        "syncSourceHost" : "",
                        "syncSourceId" : -1,
                        "infoMessage" : "",
                        "electionTime" : Timestamp(1562333094, 1),
                        "electionDate" : ISODate("2019-07-05T13:24:54Z"),
                        "configVersion" : 3,
                        "self" : true,
                        "lastHeartbeatMessage" : ""
                },
                {
                        "_id" : 1,
                        "name" : "localhost:27018",
                        "health" : 1,
                        "state" : 2,
                        "stateStr" : "SECONDARY",
                        "uptime" : 28,
                        "optime" : {
                                "ts" : Timestamp(1562333216, 1),
                                "t" : NumberLong(1)
                        },
                        "optimeDurable" : {
                                "ts" : Timestamp(1562333216, 1),
                                "t" : NumberLong(1)
                        },
                        "optimeDate" : ISODate("2019-07-05T13:26:56Z"),
                        "optimeDurableDate" : ISODate("2019-07-05T13:26:56Z"),
                        "lastHeartbeat" : ISODate("2019-07-05T13:27:05.030Z"),
                        "lastHeartbeatRecv" : ISODate("2019-07-05T13:27:04.048Z"),
                        "pingMs" : NumberLong(0),
                        "lastHeartbeatMessage" : "",
                        "syncingTo" : "localhost:27017",
                        "syncSourceHost" : "localhost:27017",
                        "syncSourceId" : 0,
                        "infoMessage" : "",
                        "configVersion" : 3
                },
                {
                        "_id" : 2,
                        "name" : "localhost:27019",
                        "health" : 1,
                        "state" : 2,
                        "stateStr" : "SECONDARY",
                        "uptime" : 24,
                        "optime" : {
                                "ts" : Timestamp(1562333216, 1),
                                "t" : NumberLong(1)
                        },
                        "optimeDurable" : {
                                "ts" : Timestamp(1562333216, 1),
                                "t" : NumberLong(1)
                        },
                        "optimeDate" : ISODate("2019-07-05T13:26:56Z"),
                        "optimeDurableDate" : ISODate("2019-07-05T13:26:56Z"),
                        "lastHeartbeat" : ISODate("2019-07-05T13:27:05.030Z"),
                        "lastHeartbeatRecv" : ISODate("2019-07-05T13:27:04.650Z"),
                        "pingMs" : NumberLong(0),
                        "lastHeartbeatMessage" : "",
                        "syncingTo" : "localhost:27018",
                        "syncSourceHost" : "localhost:27018",
                        "syncSourceId" : 1,
                        "infoMessage" : "",
                        "configVersion" : 3
                }
        ],
        "ok" : 1,
        "operationTime" : Timestamp(1562333216, 1),
        "$clusterTime" : {
                "clusterTime" : Timestamp(1562333216, 1),
                "signature" : {
                        "hash" : BinData(0,"AAAAAAAAAAAAAAAAAAAAAAAAAAA="),
                        "keyId" : NumberLong(0)
                }
        }
}
</pre> </li>
</ul>
