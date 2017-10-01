# SpringBootBlogAPI

## Tools and Technologies
<ul>
    <li>Netbeans 8.1</li>
    <li>JDK 8</li>
    <li>Spring (boot, data jpa, mvc, security, hateoas)</li>
    <li>Lombok</li>
</ul>

## How to run
<ul>
    <li>Open the project with Netbeans and right click run, or browse to source code directory and execute <code>$mvn spring-boot:run</code></li>
</ul>

## Security
<ul>
    <li>Used spring security to secure the resources with in memory user database, just for simplicity.</li>
</ul>

## Assumptions
<ul>
    <li>You can't create blog without predefined author, so you have to create the author and select it from dropdown on blog form, We can enhance this point, as from my point of view in real live the author will be a system user with a lot of data and doesn't make sense to merge the author data with blog data in one form.</li>
</ul>


