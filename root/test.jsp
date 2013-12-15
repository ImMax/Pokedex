<html>
<head>
    <script language="Javascript" type="text/javascript">

        var options = '><option value="volvo">Volvo</option><option value="saab">Saab</option><option value="fiat">Fiat</option><option value="audi">Audi</option></select>';
        <!--var input = '<input type="text" name="search">';-->
        var counter = 0;
        var limit = 10;
        function addInput(divName){
            if (counter == limit) {
                alert("Reached adding limit " + counter + " inputs");
            } else {
                counter++;

                var newSelect = document.createElement('p');
                newSelect.setAttribute('name','select' + counter);
                newSelect.innerHTML = "Entry " + counter + '<select name="select"' + counter + options <!--+ input-->;
                var newInput = document.createElement('input');
                newInput.setAttribute('type','text');
                newInput.setAttribute('name','search' + counter);

                document.getElementById(divName).insertBefore(newSelect,document.getElementById(divName).firstChild);
                document.getElementById(divName).insertBefore(newInput,document.getElementById(divName).firstChild);
            }
        }

    </script>
</head>


<body>
<!--<input type="button" value="Add filter" onClick="addInput('filters');">-->
<!--<form id="filters"/>-->
<table>
    <tr>
        <td> <!-- for filters-->
            <input type="button" value="Add filter" onClick="addInput('filters');">
            <form id="filters" action="/index.html" method="get">
                <input type="submit" value="Submit">
            </form>
        </td>
        <td> <!-- for list-->
            <table>
                <tr>
                    <td>
                        TopLeft
                    </td>
                    <td>
                        TopRight
                    </td>
                </tr>
                <tr>
                    <td>
                        BotLeft
                    </td>
                    <td>
                        BotRight
                    </td>
                </tr>
            </table>
        </td>
        <td> <!-- for stats-->

        </td>
    </tr>
</table>
<script>addInput('filters');</script>
</body>
</html>