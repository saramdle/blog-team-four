"use client";
import axios from "axios";
import { useRouter } from "next/navigation";
import { AiOutlineDelete, AiOutlineEdit } from "react-icons/ai";

type Props = {
  postId: number;
  title: string | undefined;
};

export default function Mutation({ postId, title }: Props) {
  const router = useRouter();
  const handleDelete = async () => {
    if (confirm(`정말 ${title}을 삭제하시겠습니까?`)) {
      const response = await axios.delete(
        `http://localhost:4000/posts/${postId}`
      );
      console.log(response);
      router.replace("/posts");
    }
    return;
  };

  return (
    <div className='flex cursor-pointer justify-end gap-2 text-gray-600'>
      <AiOutlineEdit size={30} />
      <AiOutlineDelete size={30} onClick={handleDelete} />
    </div>
  );
}
