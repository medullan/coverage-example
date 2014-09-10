*** Settings ***
Suite Teardown    Close Browser
Library           Selenium2Library
Library           String

*** Variables ***
${APP_URL}              http://localhost:8080

*** Test Cases ***
Open Page
    Open Browser    ${APP_URL}/helloworld/    phantomjs
    Click Link    messageLink
    Element Should Contain    message    Hello world
