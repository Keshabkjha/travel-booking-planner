<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Travel Planner - Home</title>
    <link rel="stylesheet" href="static/css/style.css">
    <style>
        body {
            background: linear-gradient(120deg, #2980b9 0%, #6dd5fa 100%);
            min-height: 100vh;
            margin: 0;
            padding: 0;
        }
        .home-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            color: #fff;
            text-align: center;
        }
        .home-title {
            font-size: 3em;
            font-weight: bold;
            margin-bottom: 0.5em;
            letter-spacing: 2px;
        }
        .home-subtitle {
            font-size: 1.5em;
            margin-bottom: 1.5em;
            font-weight: 300;
        }
        .get-started-btn {
            background: #fff;
            color: #2980b9;
            border: none;
            padding: 15px 40px;
            font-size: 1.2em;
            border-radius: 30px;
            cursor: pointer;
            font-weight: bold;
            box-shadow: 0 4px 20px rgba(0,0,0,0.1);
            transition: background 0.3s, color 0.3s;
        }
        .get-started-btn:hover {
            background: #2980b9;
            color: #fff;
        }
        .home-hero-img {
            width: 350px;
            max-width: 90vw;
            margin-bottom: 2em;
            border-radius: 20px;
            box-shadow: 0 8px 32px rgba(0,0,0,0.15);
        }
        @media (max-width: 600px) {
            .home-title { font-size: 2em; }
            .home-subtitle { font-size: 1em; }
            .home-hero-img { width: 90vw; }
        }
    </style>
</head>
<body>
    <div class="home-container">
        <img class="home-hero-img" src="https://images.unsplash.com/photo-1506744038136-46273834b3fb?auto=format&fit=crop&w=800&q=80" alt="Travel the world">
        <div class="home-title">Travel Planner & Booking</div>
        <div class="home-subtitle">
            Plan your dream trip, discover amazing destinations, and book everything in one place.<br>
            Flights, hotels, activities & more — all at your fingertips.
        </div>
        <form action="login">
            <button class="get-started-btn" type="submit">Get Started</button>
        </form>
    </div>
</body>
</html>