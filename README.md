GAPNoSqlPOC
===========

This is POC for NoSql operations with Google Cloud Datastore.

It has implementation of the following Google Cloud data store concepts:
    1. Entity <key, value>
    2. DatastoreService
    3. CRUD operations with help of Entity and Datastore.
    4. Transactions in GCD in order to achieve atomicity and concurrency.
    5. Querying on the NoSql data.
    
How to deploy Google App Engine Application?
    1. Go to https://appengine.google.com/
    2. Create application after filling all the necessory fields.
    3. Copy the application id, example. cloudigrate
    4. Paste Application id to application field of appengine-web.xml.
    5. Right click on the project and click on Google -> deploy using GAE.
    6. Application will be deployed on GAE at http://cloudigrate.appspot.com
