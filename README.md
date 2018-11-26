[![build status](https://gitlab.stackroute.in/cgi-2018/knowledge-vault/badges/v1.0.0/build.svg)](https://gitlab.stackroute.in/cgi-2018/knowledge-vault/commits/v1.0.0)
[![coverage report](https://gitlab.stackroute.in/cgi-2018/knowledge-vault/badges/v1.0.0/coverage.svg?job=codecoverage)](https://gitlab.stackroute.in/cgi-2018/knowledge-vault/commits/v1.0.0)


[![Quality Gate](http://jenkins-immersive.stackroute.in:9000/api/project_badges/measure?project=com.stackroute%3Aknowledge-vault-parent&metric=alert_status)](http://jenkins-immersive.stackroute.in:9000//dashboard/index/com.stackroute:knowledge-vault-parent)

# Knowledge Vault Product.

# Steps to download and run the product
## Clone the repository
`git clone https://gitlab.stackroute.in/cgi-2018/knowledge-vault.git`

## Run the product
Currently v1.0.0 contains the services required to create the knowledge vault. In order to run all of them, run the docker-compose file as:

`docker-compose up --build`

On local machine, send files on the following address using Postman or equivalent services on the following IP:

`localhost:8094/extractor-service/<folder-name>/extract/<file-name>`
    
where folder-name refers to the folder in the resources folder of extractor-service, and file-name is the name of the file inside the respective folder.

To process all the documents sent, and send JSON-LD object to populator service, send a GET request on the following IP using Postman:

`localhost:8080/api/v1/document`

Populator service automatically maps to Graph Database. To access Graph database, use the following IP:

`localhost:7474/browser`

After stopping the service, remove the docker images using `docker-compose down`