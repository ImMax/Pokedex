<%@ page import="pokedex.PokedexDB" %>
<%@ page import="pokedex.Pokemon" %>
<%@ page import="pokedex.Element" %>
<%@ page import="java.util.*" %>
<%@ page import="pokedex.Ability" %>
<%@ page import="java.sql.SQLException" %>
<%!
    public String outputPokemons(List<Pokemon> list) {
        String s = "<form id='tralala' action='/index.jsp' method='post'>";
        for (Pokemon pokemon : list) {
            s += "<button type='submit' name='pokemon_name' value='" + pokemon.getName() + "'><img src='/images/pokemon/ico/" + pokemon.getPicName() + "'/><br/>" + pokemon.getName() + "</button>";
        }
        s += "</form>";
        return s;
    }
%>
<%!
    public String outputElements(List<Element> list) {
        String s = "";
        for(Element e : list) {
        s += "<img class='element' src='/images/elements/" + e.getPicName() + "' Title='" + e.getName() + "'/>";
        }
        return s;
    }
%>
<%!
    public String outputAbilities(List<Ability> list) throws SQLException, ClassNotFoundException {
        String s = "";
        for(Ability a : list) {
            Element element = PokedexDB.getInstance("jdbc:h2:pokedex").searchElementById(String.valueOf(a.getElement_id())).get(0);
            s += "<img class='smallElement' src='/images/elements/" + element.getPicName() + "' Title='" + element.getName() + "'/>" + " " + a.getName() + " ";
        }
        return s;
    }
%>

<html>
    <link rel="stylesheet" type="text/css" href="/style/base.css">
    <head>
        <script language="Javascript" type="text/javascript">
            var options = '"><option value="name">Name</option><option value="species">Species</option><option value="ability">Ability</option><option value="type">Type</option><option value="weakness">Weakness</option><option value="gender">Gender</option><option value="level">Level</option></select>';
            var counter = 0;
            var limit = 7;
            function addInput(divName){
                if (counter == limit) {
                    alert("Reached adding limit " + counter + " inputs");
                } else {
                    counter++;

                    var newSelect = document.createElement('p');
                    newSelect.innerHTML = "Filter " + counter + ':<select onchange="changeDropDown(select' + counter + ')" id=select' + counter + ' name="select' + counter + options <!--+ input-->;
                    var newP = document.createElement('p');
                    newP.setAttribute('id','inputContainer' + counter);
                    newP.innerHTML = '<input type="text" class="main_input" name="search' + counter + '">';
                    document.getElementById(divName).appendChild(newSelect);
                    document.getElementById(divName).appendChild(newP);
                }
            }
        </script>
        <script>
            function changeDropDown(sender) {
                var i = sender.id.substring(sender.id.length-1,sender.length);
                var p = document.getElementById('inputContainer'+ i);
                p.innerHTML = '';
                switch (sender.value) {
                    case 'gender':
                        p.innerHTML = '<select style="width: 135px" name="search' + i + '"><option value="a">Both</option><option value="m">Male only</option><option value="f">Female only</option></select>';
                        break;
                    case 'type':
                    case 'weakness':
                        p.innerHTML = '<select style="width: 135px" name="search' + i + '">' +
                                '<option value="bug">Bug</option>' +
                                '<option value="dark">Dark</option>' +
                                '<option value="dragon">Dragon</option>' +
                                '<option value="electric">Electric</option>' +
                                '<option value="fighting">Fighting</option>' +
                                '<option value="fire">Fire</option>' +
                                '<option value="flying">Flying</option>' +
                                '<option value="ghost">Ghost</option>' +
                                '<option value="grass">Grass</option>' +
                                '<option value="ground">Ground</option>' +
                                '<option value="ice">Ice</option>' +
                                '<option value="normal">Normal</option>' +
                                '<option value="poison">Poison</option>' +
                                '<option value="psychic">Psychic</option>' +
                                '<option value="rock">Rock</option>' +
                                '<option value="steel">Steel</option>' +
                                '<option value="water">Water</option>' +
                                '</select>'
                        break;
                    case 'level':
                        p.innerHTML = '<select style="width: 135px" name="search' + i + '"><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option></select>';
                        break;
                    default:
                        p.innerHTML = '<input type="text" class="main_input" name="search' + i + '">';
                        break;
                }
            }
        </script>
        <script>
            function checkAndSubmit(select){
                var i = 1;
                var values = [];
                while(document.getElementById("select" + i)){
                    values[i-1] = document.getElementById("select" + i).value;
                    i++;
                }
                var unique = values.filter(function (e, i, arr) {
                                               return arr.lastIndexOf(e) === i;
                                           });
                if (values.length != unique.length)
                    alert("Filters must be unique!");
                else
                    document.getElementById(select).submit();
            }
        </script>
        <title>Pokedex</title>
    </head>
    <body>
        <a href="/"><img class="start_img" src="images/pokedex.png"/></a>
        <table class="main_table">
            <tr>
                <td class="search">
                    <input type="button" value="Add filter" onClick="addInput('filters');">
                    <form id="filters" name="input" action="/index.jsp" method="post">
                        <input type="button" onclick="checkAndSubmit('filters')" value="Submit">
                    </form>
                </td>
                <script>addInput('filters');</script>
                <td class="view">
                    <%
                        Map<String, String> map = new HashMap<String, String>();
                        Map<String, String[]> parameterMap = request.getParameterMap();
//                        List<Cookie> cookieList = Arrays.asList(request.getCookies());
//                        if (parameterMap.isEmpty()) {
//                            if (!cookieList.isEmpty()) {
//                                for (Cookie cookie : cookieList) {
//                                    if (!cookie.getName().equalsIgnoreCase("JSESSIONID")) {
//                                        map.put(cookie.getName(),cookie.getValue());
//                                    }
//                                }
//                            }
//                        } else {
//                            for (Cookie cookie : cookieList) {
//                                cookie.setMaxAge(0);
//                            }
//                        }
                        for(int i = 1; i <= parameterMap.values().size()/2; i++) {
                            map.put(Arrays.toString(parameterMap.get("select" + i)).replaceAll("\\[", "").replaceAll("\\]",""), Arrays.toString(parameterMap.get("search" + i)).replaceAll("\\[", "").replaceAll("\\]",""));
                        }
//                        for (Map.Entry<String,String> entry : map.entrySet()) {
//                            response.addCookie(new Cookie(entry.getKey(),entry.getValue()));
//                        }
                        PokedexDB pokedexDB = PokedexDB.getInstance("jdbc:h2:pokedex");
                        List<Pokemon> list = pokedexDB.searchPokemon(map);
                    %>
                    <%= outputPokemons(list) %>
                </td>

                <td class="info">
                    <%
                        String nameArg = request.getParameter("pokemon_name");
                        if (nameArg == null || nameArg.isEmpty()) return;
                        Pokemon pokemon = pokedexDB.searchByName(nameArg);
                        List<Element> types = pokedexDB.searchElementById(pokemon.getElementIds());
                        List<Element> weaknesses = pokedexDB.searchElementById(pokemon.getWeaknessesIds());
                        char gender = pokemon.getGender();
                        int height =  pokemon.getHeight();
                        int weight = pokemon.getWeight();
                        String species = pokedexDB.getSpeciesById(pokemon.getSpeciesId());
                        Set<String> locations = pokedexDB.getPossibleLocations(types);
                        List<Pokemon> evols = pokedexDB.getPokemonsByEvolutionId(pokemon.getEvolutionId());
                        List<Ability> abilities = pokedexDB.getAbilitiesByPokemonId(pokemon.getId());
                    %>
                    <table cellpadding="10">
                        <tr>
                            <td>
                                <h1><%= pokemon.getName() %></h1>
                                <%= "<img src='/images/pokemon/pic/" + pokemon.getPicName() + "'/>" %>
                                <h2>Type:</h2>
                                <%= outputElements(types) %>
                                <h2>Weaknesses:</h2>
                                <%= outputElements(weaknesses) %>
                            </td>
                            <td>
                                <div>
                                    <b>Species: </b> <%= species %>
                                </div>
                                <div>
                                    <b>Gender: </b> <%= (gender == 'a' ? "Both" : gender == 'm' ? "Male only" : "Female only") + ", " %>
                                    <b>Height: </b> <%= height + "ft"  + ", " %>
                                    <b>Weight: </b> <%= weight + "lbs "%>
                                </div>
                                <div>
                                    <b>Locations: </b> <%= locations.toString().replace("[","").replace("]", "") %>
                                </div>
                                <div>
                                    <b>Abilities: </b> <%= outputAbilities(abilities) %>
                                </div>
                                <b>Evolutions:</b>
                                <%= outputPokemons(evols) %>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </body>
</html>