# SpringBootBlogAPI

## Tools and Technologies
<ul>
    <li>Netbeans 8.1</li>
    <li>JDK 8</li>
    <li>Spring (boot, data jpa, mvc, security, hateoas, profiles, test)</li>
    <li>Lombok</li>
</ul>

## How to run
<ul>
    <li>Open the project with Netbeans and right click run, or browse to source code directory and execute <code>$mvn spring-boot:run</code></li>
    <li>Open the browser and point to <code>http://localhost:8086</code></li>
</ul>

## Security
<ul>
    <li>Used spring security to secure the resources with in memory user database, just for simplicity.</li>
</ul>

## System Architecture
<ul>
    <li>Hight level system architecture <a href="https://docs.google.com/drawings/d/1cE9xnAWEGTdB5xW1jE4CvCrh73DICh9mOippjBWfusc/edit?usp=sharing" target="_blank">link</a>.</li>
</ul>

## Testing
<ul>
    <li>Create integration tests for the services and Rest resources.</li>
</ul>

## Assumptions
<ul>
    <li>You can't create blog without predefined author, so you have to create the author and select it from dropdown on blog form, We can enhance this point, as from my point of view in real live the author will be a system user with a lot of data and doesn't make sense to merge the author data with blog data in one form.</li>
</ul>

## Enhancements
<ul>
    <li>Implement basic login feature with <code>UserDetails</code> instead of hard coded user.</li>
    <li>Change the authentication to use OATH2 instead of Basic authentication.</li>
    <li>Use spring ACL to secure the domain objects as now anyone can add/update.</li>
    <li>Handling exceptions and return descriptive status code on error.</li>
    <li>Document the code in a better way.</li>
</ul>