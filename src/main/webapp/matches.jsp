<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Finished Matches</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">

    <script src="js/app.js"></script>
</head>

<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="images/menu.png" alt="Logo" class="logo">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/matches">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>
    <div class="container">
        <h1>Matches</h1>

        <!-- Форма фильтрации -->
        <form method="get" action="${pageContext.request.contextPath}/matches" class="input-container">
            <input class="input-filter"
                   name="filter_by_player_name"
                   placeholder="Filter by name"
                   type="text"
                   value="${filterPlayerName}" />
            <div>
                <button type="submit" class="btn-filter">Apply Filter</button>
                <c:if test="${not empty filterPlayerName}">
                    <a href="${pageContext.request.contextPath}/matches">
                        <button type="button" class="btn-filter reset">Reset Filter</button>
                    </a>
                </c:if>
            </div>
        </form>

        <!-- Таблица матчей -->
        <table class="table-matches">
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>

            <c:choose>
                <c:when test="${not empty matchPage.matches}">
                    <c:forEach items="${matchPage.matches}" var="match">
                        <tr>
                            <td>${match.player1.name}</td>
                            <td>${match.player2.name}</td>
                            <td>
                                <span class="winner-name-td
                                    <c:if test="${match.winner.name eq match.player1.name}">winner-player1</c:if>
                                    <c:if test="${match.winner.name eq match.player2.name}">winner-player2</c:if>">
                                        ${match.winner.name}
                                </span>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="3" style="text-align: center; padding: 20px;">
                            <c:choose>
                                <c:when test="${not empty filterPlayerName}">
                                    No matches found for player "${filterPlayerName}"
                                </c:when>
                                <c:otherwise>
                                    No matches available
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </table>

        <!-- Пагинация -->
        <c:if test="${matchPage.pagesCount > 1}">
            <div class="pagination">
                <!-- Кнопка "Назад" -->
                <c:choose>
                    <c:when test="${matchPage.hasPrevious()}">
                        <a class="prev"
                           href="${pageContext.request.contextPath}/matches?page=${matchPage.currentPage - 1}&filter_by_player_name=${filterPlayerName}">
                            &lt;
                        </a>
                    </c:when>
                    <c:otherwise>
                        <span class="prev disabled">&lt;</span>
                    </c:otherwise>
                </c:choose>

                <!-- Номера страниц -->
                <c:forEach begin="1" end="${matchPage.pagesCount}" var="pageNum">
                    <c:choose>
                        <c:when test="${pageNum eq matchPage.currentPage}">
                            <span class="num-page current">${pageNum}</span>
                        </c:when>
                        <c:otherwise>
                            <a class="num-page"
                               href="${pageContext.request.contextPath}/matches?page=${pageNum}&filter_by_player_name=${filterPlayerName}">
                                    ${pageNum}
                            </a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <!-- Кнопка "Вперед" -->
                <c:choose>
                    <c:when test="${matchPage.hasNext()}">
                        <a class="next"
                           href="${pageContext.request.contextPath}/matches?page=${matchPage.currentPage + 1}&filter_by_player_name=${filterPlayerName}">
                            &gt;
                        </a>
                    </c:when>
                    <c:otherwise>
                        <span class="next disabled">&gt;</span>
                    </c:otherwise>
                </c:choose>
            </div>

            <!-- Информация о пагинации -->
            <div class="pagination-info">
                Showing ${matchPage.matches.size()} of ${matchPage.matchesCount} matches
                (Page ${matchPage.currentPage} of ${matchPage.pagesCount})
            </div>
        </c:if>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a>
            roadmap.</p>
    </div>
</footer>
</body>
</html>