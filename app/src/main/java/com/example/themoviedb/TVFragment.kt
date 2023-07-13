import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviedb.R
import com.example.themoviedb.TVAdapter
import com.example.themoviedb.model.TV
import com.example.themoviedb.model.TVResponse
import com.example.themoviedb.service.TVApiInterface
import com.example.themoviedb.service.TVApiService
import kotlinx.android.synthetic.main.fragment_t_v.view.rv_tv_list
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVFragment : Fragment() {
    private lateinit var tvAdapter: TVAdapter
    private val tvShows = mutableListOf<TV>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_t_v, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvAdapter = TVAdapter(tvShows)
        view.rv_tv_list.layoutManager = LinearLayoutManager(requireContext())
        view.rv_tv_list.adapter = tvAdapter
        getTVShows()
    }

    private fun getTVShows() {
        val apiService = TVApiService.getInstance().create(TVApiInterface::class.java)
        apiService.getTVList().enqueue(object : Callback<TVResponse> {
            override fun onResponse(call: Call<TVResponse>, response: Response<TVResponse>) {
                val tvResponse = response.body()
                if (tvResponse != null) {
                    tvShows.addAll(tvResponse.tv)
                    tvAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<TVResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
