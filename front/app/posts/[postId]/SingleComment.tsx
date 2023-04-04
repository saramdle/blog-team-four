import { useState } from "react";
import { AiOutlineDelete, AiOutlineEdit } from "react-icons/ai";

type Props = {
  id: number;
  author: string;
  contents: string;
  createdAt: string;
  postId: string;
  updateComment: (updatedComment: any) => void;
  deleteComment: (id: number) => void;
};

export default function SingleComment({
  id,
  author,
  contents,
  createdAt,
  postId,
  updateComment,
  deleteComment,
}: Props) {
  const [isEdit, setIsEdit] = useState(false);
  const [editedContents, setEditedContents] = useState(contents);

  const handleUpdate = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsEdit(!isEdit);
    const editData = {
      id,

      // 기존 author유지
      author: "유지",
      contents: editedContents,
      post: parseInt(postId),

      // db에서 자동생성될 예정
      createdAt,
      updatedAt: new Date(),
    };
    const response = await fetch(`http://localhost:4000/comments/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(editData),
    });
    const updatedComment = await response.json();
    updateComment(updatedComment);
  };

  const handleCancelEdit = () => {
    setIsEdit(false);
    setEditedContents(contents);
  };

  const handleDelete = async () => {
    const response = await fetch(`http://localhost:4000/comments/${id}`, {
      method: "DELETE",
    });

    if (response.ok) {
      await response.json();
      deleteComment(id);
    } else {
      console.error("Failed to delete comment");
    }
  };

  return (
    <div
      key={id}
      className='min-h-[100px] justify-between rounded-md bg-white p-3'
    >
      <div className='flex w-full flex-col gap-3'>
        <div className='flex justify-between'>
          <div className='text-sm font-bold'>{author}</div>
          {isEdit ? (
            <div className='flex gap-1'>
              <button className='btn-primary btn-sm btn' onClick={handleUpdate}>
                수정
              </button>
              <button
                className='btn-outline btn-sm btn'
                onClick={handleCancelEdit}
              >
                취소
              </button>
            </div>
          ) : (
            <div className='flex gap-1 text-gray-500'>
              <AiOutlineEdit
                size={20}
                className='cursor-pointer hover:scale-125 hover:text-primary'
                onClick={() => setIsEdit(!isEdit)}
              />
              <AiOutlineDelete
                size={20}
                className='cursor-pointer hover:scale-125 hover:text-primary'
                onClick={handleDelete}
              />
            </div>
          )}
        </div>
        {isEdit ? (
          <form method='PUT' className='relative'>
            <textarea
              name='comment'
              id='comment'
              rows={3}
              className='h-[70px] w-full resize-none rounded-md bg-primary/5 p-3'
              placeholder='댓글을 남겨주세요'
              value={editedContents}
              onChange={(e) => setEditedContents(e.target.value)}
            ></textarea>
            <p
              className={`absolute bottom-2 left-2 z-20 font-bold  ${
                editedContents.length > 100 ? "text-red-500" : "text-gray-500"
              }`}
            >
              {editedContents.length}/100
            </p>
          </form>
        ) : (
          <div className='py-1'>{contents}</div>
        )}

        <div className='text-sx text-gray-400'>{createdAt}</div>
      </div>
    </div>
  );
}
