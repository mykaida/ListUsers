import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listusers.User

class UserViewModel :ViewModel(){
    val users:MutableList<User> = mutableListOf()
    val currentUser: MutableLiveData <MutableList<User>> by lazy { MutableLiveData <MutableList<User>>() }
}