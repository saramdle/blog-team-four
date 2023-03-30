import moment from "moment";
import { AiOutlineDelete, AiOutlineEdit } from "react-icons/ai";

export const fetchCache = "force-no-store";

type Comment = {
  id: number;
  author: string;
  contents: string;
  post: number;
  createdAt: string;
  updatedAt: string;
};

async function getComments() {
  const res = await fetch(`http://localhost:4000/comments`);
  if (!res.ok) {
    throw new Error("Failed to fetch data");
  }
  return res.json();
}

export default async function Comments({ postId }: { postId: number }) {
  const comments = await getComments();
  const selectedComments = comments.filter(
    (comment: Comment) => comment.post == postId
  );

  return (
    <div className='my-10 flex  flex-col gap-4 shadow-sm'>
      {selectedComments.map((comment: Comment) => (
        <div
          key={comment.id}
          className='flex h-[100px] justify-between rounded-md bg-white p-3'
        >
          <div>
            <div className='text-sm font-bold'>{comment.author}</div>
            <div className='py-1 text-xl'>{comment.contents}</div>
            <div className='text-sx text-gray-400'>
              {moment(moment(comment.createdAt).format("YYYY-MM-DD")).fromNow()}
            </div>
          </div>
          <div className='text- flex gap-2 text-gray-500'>
            <AiOutlineEdit
              size={20}
              className='cursor-pointer hover:scale-125 hover:text-primary'
            />
            <AiOutlineDelete
              size={20}
              className='cursor-pointer hover:scale-125 hover:text-primary'
            />
          </div>
        </div>
      ))}
    </div>
  );
}
