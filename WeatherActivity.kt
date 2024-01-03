import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherActivity : AppCompatActivity() {

    private val apiKey = "YOUR_API_KEY" // Отримайте свій API-ключ на сайті OpenWeatherMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val repository = WeatherRepository()

        // Приклад: Отримання погоди для міста "Kyiv"
        repository.getWeather("Kyiv", apiKey).enqueue(object : Callback<WeatherData> {
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                if (response.isSuccessful) {
                    val weatherData = response.body()
                    val temperature = weatherData?.main?.temp ?: 0.0
                    val description = weatherData?.weather?.get(0)?.description ?: "N/A"

                    textViewTemperature.text = String.format("%.1f°C", temperature)
                    textViewDescription.text = description
                }
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                // Обробка помилок
            }
        })
    }
}
