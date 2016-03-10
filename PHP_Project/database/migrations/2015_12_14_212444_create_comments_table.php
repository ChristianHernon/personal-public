<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateCommentsTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('comments', function(Blueprint $table)
		{
			$table->increments('id');
//            $table->string('body');
//            $table->integer('article');
//            $table->integer('created_by')->unsigned();
//            $table->integer('modified_by')->unsigned()->nullable();
//			$table->timestamps();
//
//            $table->foreign('created_by')->references('id')->on('users');
//            $table->foreign('modified_by')->references('id')->on('users');
		});
	}

	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop('comments');
	}

}
