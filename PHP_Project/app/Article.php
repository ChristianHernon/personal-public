<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class Article extends Model
{

    /**
     * The database table used by the model.
     *
     * @var string
     */
    protected $table = 'articles';

    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'title',
        'body',
        'page',
        'area',
        'created_by',
        'modified_by'
    ];

    public function comments() {
        return $this->hasMany('App\Comment');
    }

}
