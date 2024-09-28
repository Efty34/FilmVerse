<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
</head>
<body>
    <header>
        <div class="container">
            <div id="branding">
                <h1>Filmverse</h1>
            </div>
            <nav>
                <ul>
                    <li></li>
                </ul>
            </nav>
        </div>
    </header>
    <div class="container content">
        <section>
            <h2>UI Section</h2>
            <p>Our app's UI is user-centric, combining aesthetics and functionality for an effortless experience. With responsive layouts, vibrant colors, and clear typography, it’s visually appealing and easy to navigate. Thoughtful element placement and real-time feedback enhance interaction, ensuring a seamless, engaging, and enjoyable user experience across all devices.</p>
            <h3>Features</h3>
            <ul>
                <li><strong>Recent Release:</strong> Stay updated with the latest releases. Discover newly launched content featuring the freshest additions to our app’s offerings.</li>
                <li><strong>All Time Top:</strong> Explore our all-time top-rated content. Find the most popular and critically acclaimed selections that users love and recommend.</li>
                <li><strong>Genre:</strong> Browse by genre to find content that matches your interests. From action to romance, discover a variety of categories tailored for you.</li>
                <li><strong>Random Generator:</strong> Feeling adventurous? Use our random generator to discover something new and unexpected. Perfect for when you can't decide what to choose.</li>
            </ul>
        </section>
        <section>
            <h2>Backend Section</h2>
            <p>Our app's backend is robust and scalable, ensuring high performance and reliability. Built with cutting-edge technologies, it efficiently handles data processing and storage. Advanced security measures protect user information, while APIs enable seamless integration with other services. This solid foundation supports the app’s smooth operation and future growth.</p>
        </section>
        <section>
            <h2>Design Patterns Used</h2>
            <ul>
                <li>Adapter Design Pattern</li>
                <li>Factory Design Pattern</li>
                <li>Singleton Design Pattern</li>
                <li>Observer Design Pattern</li>
            </ul>
            <h3>Adapter Design Pattern</h3>
            <p>The Adapter design pattern is commonly used in scenarios where classes with incompatible interfaces need to cooperate or when a class needs to interact with an interface that it does not implement.</p>
            <p>In our Android app, the <code>MyAdapter</code> class implements the Adapter pattern for the recycler view.</p>
            <pre><code>private void initializeRecyclerView() {
    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
    recyclerView.setLayoutManager(gridLayoutManager);
    dataList = new ArrayList<>();
    loadMoviesFromAssets(); // Load movie data
    adapter = new MyAdapter(getActivity(), dataList, false); // Enable resizing
    recyclerView.setAdapter(adapter);
}

static class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView recImage;
    TextView recTitle, recDesc;

    MyViewHolder(@NonNull View itemView) {
        super(itemView);
        recImage = itemView.findViewById(R.id.recImage);
        recTitle = itemView.findViewById(R.id.recTitle);
        recDesc = itemView.findViewById(R.id.recDesc);
    }
}

@NonNull
@Override
public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
    return new MyViewHolder(view);
}

@Override
public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    DataClass movie = dataList.get(position);
    holder.recTitle.setText(movie.getTitle());
    holder.recDesc.setText(movie.getRating());
    Picasso.get().load(movie.getPosterUrl()).into(holder.recImage);
}</code></pre>
            <h3>Factory Design Pattern</h3>
            <p>The Factory Design Pattern is used to create instances of the <code>DataClass</code> from JSON data.</p>
            <pre><code>package com.example.swetry;
import org.json.JSONObject;

public class DataClassFactory {
    public static DataClass createFromJson(JSONObject movie) throws Exception {
        String title = movie.getString("Title");
        String rating = movie.getJSONArray("Ratings").getJSONObject(0).getString("Value");
        String posterUrl = movie.getString("Poster");
        String yearRelease = movie.getString("Year");
        return new DataClass(title, rating, posterUrl, yearRelease, movie.getString("Genre"), movie.getString("Plot"));
    }
}</code></pre>
            <h3>Singleton Design Pattern</h3>
            <p>Ensures that only one instance of the <code>MovieRepository</code> class is created throughout the application's lifecycle.</p>
            <pre><code>public static synchronized MovieRepository getInstance(Context context) {
    if (instance == null) {
        instance = new MovieRepository(context);
    }
    return instance;
}</code></pre>
            <h3>Observer Design Pattern</h3>
            <p>List of Observers:</p>
            <pre><code>private List&lt;MovieDataObserver&gt; observers = new ArrayList&lt;&gt;();

public void addObserver(MovieDataObserver observer) { observers.add(observer); }
public void removeObserver(MovieDataObserver observer) { observers.remove(observer); }

private void notifyObservers() {
    for (MovieDataObserver observer : observers) {
        observer.onMovieDataChanged();
    }
}</code></pre>
            <p>The Observer pattern is used to establish a one-to-many dependency between objects, ensuring that when one object changes state, all its dependents are notified and updated automatically.</p>
        </section>
        <section class="diagrams">
            <h2>Diagrams</h2>
            <h3>Class Diagram</h3>
            ![class diagram](https://github.com/Efty34/SWE_Project/assets/132547691/0a0bdda1-24d2-418b-8c06-b4ff0eac036e)
            <h3>Use Case Diagram</h3>
            ![use case](https://github.com/Efty34/SWE_Project/assets/132547691/c1b35724-74b9-447f-971a-5b551ddb943f)
            <h3>Sequence Diagram</h3>
            ![sequnce diagram](https://github.com/Efty34/SWE_Project/assets/132547691/67699861-89bb-40e4-8df0-50c439ab111c)
        </section>
        <section>
            <h2>License</h2>
            <p>This project is licensed under the MIT License - see the <a href="LICENSE.md">LICENSE.md</a> file for details.</p>
        </section>
    </div>
</body>
</html>
