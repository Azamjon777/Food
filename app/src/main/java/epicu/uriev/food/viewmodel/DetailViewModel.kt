package epicu.uriev.food.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import epicu.uriev.food.REALISATION
import epicu.uriev.food.model.BasicItemPojo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    fun insertItem(basicItemPojo: BasicItemPojo, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            REALISATION.insertBasicItem(basicItemPojo) {
                onSuccess()
            }
            1+1
        }

    fun deleteItem(basicItemPojo: BasicItemPojo, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            REALISATION.deleteBasicItem(basicItemPojo) {
                onSuccess()
            }
            2+2
        }
}